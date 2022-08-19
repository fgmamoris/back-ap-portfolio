/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.service.InteresService;
import com.mamoris.portfolio.entity.Interes;
import com.mamoris.portfolio.entity.Persona;
import com.mamoris.portfolio.service.PersonaService;
import com.mamoris.portfolio.utils.Mensaje;
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
@RequestMapping("/api/interest")
@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class InteresController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    InteresService interesService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/interests")
    @ResponseBody
    public ResponseEntity<List<Interes>> getAll() {
        List<Interes> list = interesService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Interes> create(@Valid @RequestBody Interes interesDTO) {
        if (interesService.getAll().isEmpty()) {

            if (StringUtils.isBlank(interesDTO.getDescripcion())) {
                return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
            }

            if (interesDTO.getPersona() == null) {
                return new ResponseEntity(new Mensaje("La persona es obligatoria"), HttpStatus.BAD_REQUEST);
            } else if (interesDTO.getPersona().getId() == null) {
                return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
            } else if (personaService.getAll().isEmpty()) {
                return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
            } else if (!personaService.existsById(interesDTO.getPersona().getId())) {
                return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
            } else if (personaService.getById(interesDTO.getPersona().getId()).getInteres() != null) {
                return new ResponseEntity(new Mensaje("No se puede crear registro, la persona ya cuenta con una asociación, debera cambiar el endpoint para actualizar el registro"), HttpStatus.BAD_REQUEST);
            }
            Interes interes = new Interes();
            interes.setDescripcion(interesDTO.getDescripcion());
            Persona persona = personaService.getById(interesDTO.getPersona().getId());
            interes.setPersona(persona);
            interes = interesService.save(interes);
            persona.setInteres(interes);
            personaService.save(persona);

            return new ResponseEntity<Interes>(interes, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(new Mensaje("No se puede crear registro, la persona ya cuenta con una asociación, debera cambiar el endpoint para actualizar el registro"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interes> getById(@PathVariable("id") Long id) {
        System.out.println(id);
        if (!interesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Interes interes = interesService.getById(id);
        return new ResponseEntity(interes, HttpStatus.OK);
    }

    @GetMapping("/byperson/{id}")
    public ResponseEntity<Interes> getByPersonId(@PathVariable("id") Long id) {
        if (interesService.getAll().size() == 1) {
            if (!interesService.existsByPersonaId(id)) {
                return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
            }
            Interes interes = interesService.getByPersonaId(id);
            return new ResponseEntity(interes, HttpStatus.OK);
        } else {
            return new ResponseEntity(new Mensaje("No se puede crear registro, verificque el id en la BBDD"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Interes> update(@PathVariable("id") Long id,@Valid @RequestBody Interes interesDTO) {
        if (StringUtils.isBlank(interesDTO.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (!interesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro con el parametro id ingresado"), HttpStatus.NOT_FOUND);
        }
         if (!interesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Interes interesUpdated = interesService.getById(id);
        interesUpdated.setDescripcion(interesDTO.getDescripcion());
        interesUpdated = interesService.save(interesUpdated);
        return new ResponseEntity(interesUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!interesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        interesService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
