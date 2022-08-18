package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Acerca;
import com.mamoris.portfolio.service.impl.IAcercaService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamoris.portfolio.repository.AcercaRepository;

/**
 *
 * @author Federico Mamoris
 */
@Service

public class AcercaService implements IAcercaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private AcercaRepository repo;

    @Override
    public List<Acerca> getAll() {
        return repo.findAll();
    }

    @Override
    public Acerca save(Acerca acerca) {
        return repo.save(acerca);
    }

    @Override
    public Acerca getById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public boolean existsByPersonaId(Long id) {
        return repo.existsByPersonaId(id);

    }

    @Override
    public Acerca getByPersonaId(Long id) {
        return repo.getByPersonaId(id);

    }
}
