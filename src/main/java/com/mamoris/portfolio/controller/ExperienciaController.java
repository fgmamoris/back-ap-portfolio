/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.service.ExperienciaService;
import com.mamoris.portfolio.service.PersonaService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mamoris.portfolio.entity.Experiencia;
import com.mamoris.portfolio.entity.Persona;
import com.mamoris.portfolio.utils.Mensaje;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/experience")
//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
public class ExperienciaController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    ExperienciaService experienciaService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/experiences")
    @ResponseBody
    public ResponseEntity<List<Experiencia>> getAll() {
        List<Experiencia> list = experienciaService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Experiencia> create(@Valid @RequestBody Experiencia experienciaDTO) {
        if (StringUtils.isBlank(experienciaDTO.getPuesto())) {
            return new ResponseEntity(new Mensaje("El Puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciaDTO.getCompania())) {
            return new ResponseEntity(new Mensaje("La compañia es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fechaInicio es obligatoria(yyyy-MM-dd)"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getPersona() == null) {
            return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (experienciaDTO.getPersona().getId() == null) {
            return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
        } else if (!personaService.existsById(experienciaDTO.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
        }
        if (experienciaDTO.getFechaFin() == null && !experienciaDTO.isActual()) {
            return new ResponseEntity(new Mensaje("Debe ingresar si es trabajo actual o la fechaFin"), HttpStatus.BAD_REQUEST);
        } else if (experienciaDTO.getFechaFin() != null && experienciaDTO.isActual()) {
            return new ResponseEntity(new Mensaje("Trabajo actual no puede tener fechaFin"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getFechaFin() != null) {
            if (experienciaDTO.getFechaInicio().compareTo(experienciaDTO.getFechaFin()) > 0) {
                return new ResponseEntity(new Mensaje("La fechaInicio no puede ser posterior a fechaFin"), HttpStatus.BAD_REQUEST);
            }
        }
        Experiencia experiencia;
        Persona persona = personaService.getById(experienciaDTO.getPersona().getId());
        //experiencia.setPersona(persona);
        experiencia = experienciaService.save(experienciaDTO);

        return new ResponseEntity<>(experiencia, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") Long id
    ) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = experienciaService.getById(id);
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Experiencia> update(@PathVariable("id") Long id,@Valid @RequestBody Experiencia experienciaDTO
    ) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(experienciaDTO.getPuesto())) {
            return new ResponseEntity(new Mensaje("el puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fecha de inicio es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getFechaFin() == null && !experienciaDTO.isActual()) {
            return new ResponseEntity(new Mensaje("Debe ingresar si es trabajo actual o la fechaFin"), HttpStatus.BAD_REQUEST);
        } else if (experienciaDTO.getFechaFin() != null && experienciaDTO.isActual()) {
            return new ResponseEntity(new Mensaje("Trabajo actual no puede tener fechaFin"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaDTO.getFechaFin() != null) {
            if (experienciaDTO.getFechaInicio().compareTo(experienciaDTO.getFechaFin()) > 0) {
                return new ResponseEntity(new Mensaje("La fechaInicio no puede ser posterior a fechaFin"), HttpStatus.BAD_REQUEST);
            }
        }
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = experienciaService.getById(id);
        experiencia.setPuesto(experienciaDTO.getPuesto());
        experiencia.setFechaInicio(experienciaDTO.getFechaInicio());
        if (experienciaDTO.getCompania() != null) {
            experiencia.setCompania(experienciaDTO.getCompania());
        }
        if (experienciaDTO.getDescripcion() != null) {
            experiencia.setDescripcion(experienciaDTO.getDescripcion());
        }
        if (experienciaDTO.getFechaFin() != null) {
            experiencia.setFechaFin(experienciaDTO.getFechaFin());
        }
        System.out.println(experienciaDTO.isActual());
        if (experienciaDTO.isActual()) {
            experiencia.setFechaFin(null);
            experiencia.setActual(true);
        } else {
            experiencia.setActual(false);
        }
        experiencia = experienciaService.save(experiencia);
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id
    ) {
        if (!experienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        experienciaService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
