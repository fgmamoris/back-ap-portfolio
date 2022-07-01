package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {

    
}
