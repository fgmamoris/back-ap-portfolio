package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    public boolean existsById(Long id);
}
