package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Skill;
import com.mamoris.portfolio.repository.SkillRepository;
import com.mamoris.portfolio.service.impl.ISkillService;
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
public class SkillService implements ISkillService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private SkillRepository repo;

    @Override
    public List<Skill> getAll() {
        return repo.findAll();
    }

    @Override
    public Skill save(Skill user) {
        return repo.save(user);
    }

    @Override
    public Skill getById(Long id) {
        return repo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
