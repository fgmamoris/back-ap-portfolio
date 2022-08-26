/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.service.PersonaService;
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
@RequestMapping("/api/v1/person")
@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class PersonaController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    PersonaService personaService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Persona>> getAll() {
        List<Persona> list = personaService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Persona> create(@Valid @RequestBody Persona personaDTO) {

        if (StringUtils.isBlank(personaDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDTO.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDTO.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (!Validate.validateEmail(personaDTO.getEmail())) {
            return new ResponseEntity(new Mensaje("Dirección de correo electrónico con formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        if (!personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("Ya hay un registro en la bbdd"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.save(personaDTO);
        return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }

        List<Persona> persona = personaService.getAll();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> update(@PathVariable("id") Long id, @Valid @RequestBody Persona personaDTO) {
        if (StringUtils.isBlank(personaDTO.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDTO.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDTO.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        /*if (personaDTO.getCertificados() == null || personaDTO.getCertificados().isEmpty()) {
            return new ResponseEntity(new Mensaje("Debe contener al menos un certificado"), HttpStatus.BAD_REQUEST);
        }
        if (personaDTO.getEducacion() == null || personaDTO.getEducacion().isEmpty()) {
            return new ResponseEntity(new Mensaje("Debe contener al menos una educación"), HttpStatus.BAD_REQUEST);
        }
        if (personaDTO.getExperiencias() == null || personaDTO.getExperiencias().isEmpty()) {
            return new ResponseEntity(new Mensaje("Debe contener al menos una experiencia"), HttpStatus.BAD_REQUEST);
        }
        if (personaDTO.getInteres() == null) {
            return new ResponseEntity(new Mensaje("El interes es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (personaDTO.getRedesSociales() == null || personaDTO.getRedesSociales().isEmpty()) {
            return new ResponseEntity(new Mensaje("Debe contener al menos una red social"), HttpStatus.BAD_REQUEST);
        }
        if (personaDTO.getRedesSociales() == null || personaDTO.getRedesSociales().isEmpty()) {
            return new ResponseEntity(new Mensaje("Debe contener al menos un skill"), HttpStatus.BAD_REQUEST);
        }*/
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        if (!Validate.validateEmail(personaDTO.getEmail())) {
            return new ResponseEntity(new Mensaje("Dirección de correo electrónico con formato incorrecto"), HttpStatus.BAD_REQUEST);
        }
        Persona personaUpdated = personaService.getById(id);

        personaUpdated.setNombre(personaDTO.getNombre());
        personaUpdated.setApellido(personaDTO.getApellido());
        personaUpdated.setDireccion(personaDTO.getDireccion());
        personaUpdated.setEmail(personaDTO.getEmail());
        personaUpdated.setCertificados(personaDTO.getCertificados());
        personaUpdated.setEducacion(personaDTO.getEducacion());
        personaUpdated.setExperiencias(personaDTO.getExperiencias());
        personaUpdated.setInteres(personaDTO.getInteres());
        personaUpdated.setRedesSociales(personaDTO.getRedesSociales());
        personaUpdated.setSkill(personaDTO.getSkill());
        personaUpdated.setId(id);
        personaUpdated = personaService.save(personaUpdated);;
        return new ResponseEntity(personaUpdated, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        personaService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }

}
