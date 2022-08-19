/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.service.AcercaService;
import com.mamoris.portfolio.entity.Acerca;
import com.mamoris.portfolio.service.PersonaService;
import com.mamoris.portfolio.utils.Mensaje;
import java.util.List;
import javax.persistence.EntityNotFoundException;
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
@RequestMapping("/api/about")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
public class AcercaController {

    private final static Logger Log = LoggerFactory.getLogger(AcercaController.class);

    @Autowired
    AcercaService acercaService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/abouts")
    
    @ResponseBody
    public ResponseEntity<List<Acerca>> getAll() {
        List<Acerca> list = acercaService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<Acerca> create(@Valid @RequestBody Acerca acercaDTO) throws EntityNotFoundException {
        if (acercaService.getAll().isEmpty()) {
            if (StringUtils.isBlank(acercaDTO.getDescripcion()) || acercaDTO.getDescripcion() == null) {
                return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
            }
            if (acercaDTO.getPersona() == null) {
                return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
            } else if (acercaDTO.getPersona().getId() == null) {
                return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
            } else if (personaService.getAll().isEmpty()) {
                return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
            } else if (!personaService.existsById(acercaDTO.getPersona().getId())) {
                return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
            } else if (personaService.getById(acercaDTO.getPersona().getId()).getAcerca() != null) {
                return new ResponseEntity(new Mensaje("No se puede crear registro, la persona ya cuenta con una asociación, debera cambiar el endpoint para actualizar el registro"), HttpStatus.BAD_REQUEST);
            }
            Acerca acerca = acercaService.save(acercaDTO);
            return new ResponseEntity< Acerca>(acerca, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(new Mensaje("No se puede crear registro, ya se encuentran cargados los datos en la BBDD"), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Acerca> getById(@PathVariable("id") Long id) {
        if (!acercaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Acerca acerca = acercaService.getById(id);
        return new ResponseEntity(acerca, HttpStatus.OK);
    }

    @GetMapping("/byperson/{id}")
    public ResponseEntity<Acerca> getByPersonId(@PathVariable("id") Long id) {
        if (!acercaService.existsByPersonaId(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Acerca acerca = acercaService.getByPersonaId(id);
        return new ResponseEntity(acerca, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Acerca> update(@PathVariable("id") Long id, @Valid @RequestBody Acerca acercaDTO) {
        if (acercaService.getAll().size() == 1) {
            if (StringUtils.isBlank(acercaDTO.getDescripcion())) {
                return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
            }
            if (!acercaService.existsById(id)) {
                return new ResponseEntity(new Mensaje("No existe registro con el parametro id ingresado"), HttpStatus.NOT_FOUND);
            }
            if (!acercaService.existsById(id)) {
                return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
            }
            Acerca acercaUpdated = acercaService.getById(id);
            acercaUpdated.setDescripcion(acercaDTO.getDescripcion());
            acercaUpdated = acercaService.save(acercaUpdated);
            return new ResponseEntity(acercaUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No se puede crear registro, verificque el id en la BBDD"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!acercaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        acercaService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
