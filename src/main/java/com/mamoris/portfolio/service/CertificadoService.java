package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Certificado;
import com.mamoris.portfolio.repository.CertificadoRepository;
import com.mamoris.portfolio.service.impl.ICertificadoService;
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
public class CertificadoService implements ICertificadoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificadoService.class);

    @Autowired
    private CertificadoRepository repo;

    @Override
    public List<Certificado> getAll() {
        return repo.findAll();
    }

    @Override
    public Certificado getById(Long id) {
        return repo.getById(id);
    }

    @Override
    public Certificado save(Certificado user) {
        return repo.save(user);
    }

    @Override
    public void deleteById(Long id) {

        repo.deleteById(id);
    }

}
