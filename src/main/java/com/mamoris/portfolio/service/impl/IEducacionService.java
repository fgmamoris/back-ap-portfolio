package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Educacion;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IEducacionService {

    public List<Educacion> getAll();

    public Educacion getById(Long id);

    public Educacion save(Educacion educacion);

    public void deleteById(Long id);

    public boolean existsById(Long id);

}
