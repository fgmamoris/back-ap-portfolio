package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Interes;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IInteresService {

    public List<Interes> getAll();

    public Interes getById(Long id);

    public Interes save(Interes interes);

    public void deleteById(Long id);

    public boolean existsById(Long id);

    public boolean existsByPersonaId(Long id);

    public Interes getByPersonaId(Long id);

}
