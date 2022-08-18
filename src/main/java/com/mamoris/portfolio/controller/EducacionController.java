/*
 */
package com.mamoris.portfolio.controller;

import com.mamoris.portfolio.utils.Mensaje;
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
import com.mamoris.portfolio.entity.Educacion;
import com.mamoris.portfolio.entity.Persona;
import com.mamoris.portfolio.service.EducacionService;
import com.mamoris.portfolio.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class EducacionController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    EducacionService educacionService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/educations")
    @ResponseBody
    public ResponseEntity<List<Educacion>> getAll() {
        List<Educacion> list = educacionService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Educacion> create(@Valid @RequestBody Educacion educacionDTO) {

        //descripcion	fecha_fin	fecha_inicio	institucion	titulo	persona_id
        if (StringUtils.isBlank(educacionDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDTO.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La  intitutucion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (educacionDTO.getFechaFin() == null || educacionDTO.getFechaFin() == null) {
            return new ResponseEntity(new Mensaje("Las fehcas de inicio y de fin son obligatorias"), HttpStatus.BAD_REQUEST);
        }
        if (educacionDTO.getPersona() == null) {
            return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (educacionDTO.getPersona().getId() == null) {
            return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
        } else if (!personaService.existsById(educacionDTO.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = new Educacion();
        Persona persona = personaService.getById(educacionDTO.getPersona().getId());
        //experiencia.setPersona(persona);
        educacion = educacionService.save(educacionDTO);

        return new ResponseEntity<Educacion>(educacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") Long id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getById(id);
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Educacion> update(@PathVariable("id") Long id,@Valid @RequestBody Educacion educacionDTO) {
        //descripcion	fecha_fin	fecha_inicio	institucion	titulo	persona_id
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(educacionDTO.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionDTO.getInstitucion())) {
            return new ResponseEntity(new Mensaje("La  intitutucion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (educacionDTO.getFechaFin() == null || educacionDTO.getFechaFin() == null) {
            return new ResponseEntity(new Mensaje("Las fehcas de inicio y de fin son obligatorias"), HttpStatus.BAD_REQUEST);
        }
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getById(id);
        educacion.setTitulo(educacionDTO.getTitulo());
        educacion.setInstitucion(educacionDTO.getInstitucion());
        if (educacionDTO.getDescripcion() != null) {
            educacion.setDescripcion(educacionDTO.getDescripcion());
        }
        if (educacionDTO.getFechaInicio() != null) {
            educacion.setFechaInicio(educacionDTO.getFechaInicio());
        }
        if (educacionDTO.getFechaFin() != null) {
            educacion.setFechaFin(educacionDTO.getFechaFin());
        }
        educacion = educacionService.save(educacion);
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        educacionService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
