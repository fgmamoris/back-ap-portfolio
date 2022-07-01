package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface IntereslRepository extends JpaRepository<Interes, Long> {

    
}
