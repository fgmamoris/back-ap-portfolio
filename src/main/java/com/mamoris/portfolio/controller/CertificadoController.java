/*
 */
package com.mamoris.portfolio.controller;

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
import com.mamoris.portfolio.entity.Certificado;

import com.mamoris.portfolio.service.CertificadoService;
import com.mamoris.portfolio.service.PersonaService;
import com.mamoris.portfolio.utils.Mensaje;
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
@RequestMapping("/api/v1/certificate")
@CrossOrigin(origins = "https://portfolio-fm.firebaseapp.com")
public class CertificadoController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @Autowired
    CertificadoService certificadoService;

    @Autowired
    PersonaService personaService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<List<Certificado>> getAll() {
        List<Certificado> list = certificadoService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Certificado> create(@Valid @RequestBody Certificado certificadoDTO) {

        if (StringUtils.isBlank(certificadoDTO.getNombreCurso())) {
            return new ResponseEntity(new Mensaje("El nombreCurso obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (certificadoDTO.getPersona() == null) {
            return new ResponseEntity(new Mensaje("El objeto persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (certificadoDTO.getPersona().getId() == null) {
            return new ResponseEntity(new Mensaje("El id de persona es obligatorio"), HttpStatus.BAD_REQUEST);
        } else if (personaService.getAll().isEmpty()) {
            return new ResponseEntity(new Mensaje("No hay personas registradas, deber?? crear primero un registro de persona"), HttpStatus.BAD_REQUEST);
        } else if (!personaService.existsById(certificadoDTO.getPersona().getId())) {
            return new ResponseEntity(new Mensaje("No existe registro de persona con el parametro id"), HttpStatus.NOT_FOUND);
        }

        if ((!certificadoDTO.getUrlCertificado().toLowerCase().startsWith("http://") && !certificadoDTO.getUrlCertificado().toLowerCase().startsWith("https://")) && !certificadoDTO.getUrlCertificado().isEmpty()) {
            certificadoDTO.setUrlCertificado("https://" + certificadoDTO.getUrlCertificado().toLowerCase());
        } else {
            certificadoDTO.setUrlCertificado(certificadoDTO.getUrlCertificado().toLowerCase());
        }

        Certificado certificado = certificadoService.save(certificadoDTO);
        return new ResponseEntity<Certificado>(certificado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificado> getById(@PathVariable("id") Long id) {
        if (!certificadoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }

        Certificado certificado = certificadoService.getById(id);
        return new ResponseEntity(certificado, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificado> update(@PathVariable("id") Long id, @Valid @RequestBody Certificado certificadoDTO) {

        if (!certificadoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(certificadoDTO.getNombreCurso())) {
            return new ResponseEntity(new Mensaje("El nombreCurso obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (!certificadoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }

        Certificado certificadoUpdated = certificadoService.getById(id);
        certificadoUpdated.setNombreCurso(certificadoDTO.getNombreCurso());

        if ((!certificadoDTO.getUrlCertificado().toLowerCase().startsWith("http://") && !certificadoDTO.getUrlCertificado().toLowerCase().startsWith("https://")) && !certificadoDTO.getUrlCertificado().isEmpty()) {
            certificadoDTO.setUrlCertificado("https://" + certificadoDTO.getUrlCertificado().toLowerCase());

        } else {
            certificadoUpdated.setUrlCertificado(certificadoDTO.getUrlCertificado().toLowerCase());
        }
        certificadoUpdated = certificadoService.save(certificadoUpdated);
        return new ResponseEntity(certificadoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!certificadoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe registro"), HttpStatus.NOT_FOUND);
        }
        certificadoService.deleteById(id);
        return new ResponseEntity(new Mensaje("Registro eliminado"), HttpStatus.OK);

    }
}
