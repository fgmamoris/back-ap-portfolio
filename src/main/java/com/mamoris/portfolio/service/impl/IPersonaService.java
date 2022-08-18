package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Federico Mamoris
 */
public interface IPersonaService {

    public List<Persona> getAll();

    public Persona save(Persona persona);

    public Persona getById(Long id);

    public void deleteById(Long id);

    public boolean existsById(Long id);
}
