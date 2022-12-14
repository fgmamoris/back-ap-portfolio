package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Experiencia;
import com.mamoris.portfolio.repository.ExperienciaRepository;
import com.mamoris.portfolio.service.impl.IExperienciaService;
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
public class ExperienciaService implements IExperienciaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private ExperienciaRepository repo;

    @Override
    public List<Experiencia> getAll() {
        return repo.findAll();
    }

    @Override
    public Experiencia save(Experiencia experiencia) {
        return repo.save(experiencia);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Experiencia getById(Long id) {
        return repo.findById(id).get();
    }

    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

}
