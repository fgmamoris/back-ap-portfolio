/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.service.RedSocialService;
import com.mamoris.portfolio.service.PersonaService;
import com.mamoris.portfolio.entity.RedSocial;
import com.mamoris.portfolio.entity.Persona;
import com.mamoris.portfolio.utils.Mensaje;
import com.mamoris.portfolio.utils.Validate;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/socialmedia")
@CrossOrigin(origins = "*")

public class RedSolcialController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    RedSocialService redSocialService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/socialmedias")
    @ResponseBody
    public ResponseEntity<List<RedSocial>> getAll() {
        List<RedSocial> list = redSocialService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RedSocial> create(@Valid @RequestBody RedSocial redSocialDTO) {
        if (StringUtils.isBlank(redSocialDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (!Validate.validateIcon(redSocialDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("Dirección de correo electrónico con formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(redSocialDTO.getUrlRedSocial())) {
            return new ResponseEntity(new Mensaje("La urlRedSocial es obligatoria"), HttpStatus.BAD_REQUEST);
        } else if (!Validate.validateURL(redSocialDTO.getUrlRedSocial())) {
            return new ResponseEntity(new Mensaje("Dirección de correo electrónico con formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (redSocialDTO.getPersona() == null) {
            return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (redSocialDTO.getPersona().getId() == null) {
            return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
        } else if (!personaService.existsById(redSocialDTO.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
        }

        if (!redSocialDTO.getUrlRedSocial().toLowerCase().startsWith("http://")) {
            redSocialDTO.setUrlRedSocial("http://" + redSocialDTO.getUrlRedSocial().toLowerCase());
        } else {
            redSocialDTO.setUrlRedSocial(redSocialDTO.getUrlRedSocial().toLowerCase());
        }
        RedSocial red = redSocialService.save(redSocialDTO);
        return new ResponseEntity<>(red, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RedSocial> getById(@PathVariable("id") Long id) {
        if (!redSocialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        RedSocial redSocial = redSocialService.getById(id);
        return new ResponseEntity(redSocial, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RedSocial> update(@PathVariable("id") Long id, @Valid @RequestBody RedSocial redSocialDTO) {
        if (StringUtils.isBlank(redSocialDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (!Validate.validateIcon(redSocialDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono contiene un formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(redSocialDTO.getUrlRedSocial())) {
            return new ResponseEntity(new Mensaje("La urlRedSocial es obligatoria"), HttpStatus.BAD_REQUEST);
        } else if (!Validate.validateURL(redSocialDTO.getUrlRedSocial())) {
            return new ResponseEntity(new Mensaje("La urlRedSocial contiene un formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (!redSocialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro con el parametro id ingresado"), HttpStatus.NOT_FOUND);
        }
        if (!redSocialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        RedSocial redUpdated = redSocialService.getById(id);
        redUpdated.setNombreIcono(redSocialDTO.getNombreIcono());
        if (!redSocialDTO.getUrlRedSocial().toLowerCase().startsWith("http://")) {
            redUpdated.setUrlRedSocial("http://" + redSocialDTO.getUrlRedSocial().toLowerCase());
        } else {
            redUpdated.setUrlRedSocial(redSocialDTO.getUrlRedSocial().toLowerCase());
        }

        redUpdated = redSocialService.save(redUpdated);
        return new ResponseEntity(redUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!redSocialService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        redSocialService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
