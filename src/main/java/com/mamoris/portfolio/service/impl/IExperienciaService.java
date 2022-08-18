package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Experiencia;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IExperienciaService {

    public List<Experiencia> getAll();

    public Experiencia getById(Long id);

    public Experiencia save(Experiencia experiencia);

    public void deleteById(Long id);

    public boolean existsById(Long id);
}
