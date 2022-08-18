package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Persona;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamoris.portfolio.repository.PersonaRepository;
import com.mamoris.portfolio.service.impl.IPersonaService;

/**
 *
 * @author Federico Mamoris
 */
@Service
public class PersonaService implements IPersonaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaService.class);

    @Autowired
    private PersonaRepository repo;

    @Override
    public List<Persona> getAll() {
        return repo.findAll();
    }

    @Override
    public Persona getById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Persona save(Persona persona) {

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
}
