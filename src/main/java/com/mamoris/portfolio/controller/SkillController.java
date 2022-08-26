/*
 */
package com.mamoris.portfolio.controller;


import com.mamoris.portfolio.service.SkillService;
import com.mamoris.portfolio.entity.Skill;
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
@RequestMapping("/api/v1/skill")

@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
public class SkillController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    SkillService skillService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Skill>> getAll() {
        List<Skill> list = skillService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Skill> create(@Valid @RequestBody Skill skillDTO) {
        if (StringUtils.isBlank(skillDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        /*else if (!Validate.validateIcon(skillDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono contiene un formato incorrecto"), HttpStatus.BAD_REQUEST);
        }*/
        if (skillDTO.getPersona() == null) {
            return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (skillDTO.getPersona().getId() == null) {
            return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay personas registradas, deberá crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
        } else if (!personaService.existsById(skillDTO.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
        }

        Skill skill = skillService.save(skillDTO);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") Long id) {
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Skill redSocial = skillService.getById(id);
        return new ResponseEntity(redSocial, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> update(@PathVariable("id") Long id, @Valid @RequestBody Skill skillDTO) {
        if (StringUtils.isBlank(skillDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        /*else if (!Validate.validateIcon(skillDTO.getNombreIcono())) {
            return new ResponseEntity(new Mensaje("El nombreIcono contiene un formato incorrecto"), HttpStatus.BAD_REQUEST);
        }*/
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro con el parametro id ingresado"), HttpStatus.NOT_FOUND);
        }
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        Skill skillUpdated = skillService.getById(id);
        skillUpdated.setNombreIcono(skillDTO.getNombreIcono());
        skillUpdated = skillService.save(skillUpdated);
        return new ResponseEntity<>(skillUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!skillService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        skillService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);
    }
}
