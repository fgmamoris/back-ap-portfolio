package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Skill;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface IInteresService {

    public List<Skill> getAll();

    public Skill getById(Long id);

    public Skill save(Skill user);

    public void deleteById(Long id);


}
