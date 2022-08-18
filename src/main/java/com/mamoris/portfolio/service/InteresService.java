package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Interes;
import com.mamoris.portfolio.service.impl.IInteresService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamoris.portfolio.repository.InteresRepository;

/**
 *
 * @author Federico Mamoris
 */
@Service

public class InteresService implements IInteresService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private InteresRepository repo;

    @Override
    public List<Interes> getAll() {
        return repo.findAll();
    }

    @Override
    public Interes save(Interes interes) {
        return repo.save(interes);
    }

    @Override
    public Interes getById(Long id) {
        return repo.getById(id);
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
    public Interes getByPersonaId(Long id) {
        return repo.getByPersonaId(id);
    }

}
