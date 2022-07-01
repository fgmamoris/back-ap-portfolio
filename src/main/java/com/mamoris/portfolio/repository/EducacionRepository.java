package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Long> {

    
}
