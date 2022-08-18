package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.RedSocial;
import com.mamoris.portfolio.repository.RedSocialRepository;
import com.mamoris.portfolio.service.impl.IRedSocialService;
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
public class RedSocialService implements IRedSocialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private RedSocialRepository repo;

    @Override
    public List<RedSocial> getAll() {
        return repo.findAll();
    }

    @Override
    public RedSocial save(RedSocial redSocial) {
        return repo.save(redSocial);
    }

    @Override
    public RedSocial getById(Long id) {
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
}
