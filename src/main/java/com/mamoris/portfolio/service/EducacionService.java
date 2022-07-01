package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Educacion;
import com.mamoris.portfolio.repository.EducacionRepository;
import com.mamoris.portfolio.service.impl.IEducacionService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Federico Mamoris
 */
@Service
public class EducacionService implements IEducacionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private EducacionRepository repo;

    @Override
    public List<Educacion> getAll() {
        return repo.findAll();
    }

    @Override
    public Educacion save(Educacion user) {
        return repo.save(user);
    }

    @Override
    public Educacion getById(Long id) {        
        return repo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
