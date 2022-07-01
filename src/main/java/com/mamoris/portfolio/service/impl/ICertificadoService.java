package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Certificado;
import java.util.List;

/**
 *
 * @author Federico Mamoris
 */
public interface ICertificadoService {

    public List<Certificado> getAll();

    public Certificado getById(Long id);

    public Certificado save(Certificado user);

    public void deleteById(Long id);


}
