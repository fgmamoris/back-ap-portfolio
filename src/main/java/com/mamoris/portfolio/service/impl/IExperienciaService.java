package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Experiencia;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IExperienciaService {

    public List<Experiencia> getAll();

    public Experiencia getExperienciaById(Long id);

    public Experiencia save(Experiencia user);

    public void deleteById(Long id);


}
