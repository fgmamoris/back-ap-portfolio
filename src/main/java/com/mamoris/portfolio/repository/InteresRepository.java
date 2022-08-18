package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface InteresRepository extends JpaRepository<Interes, Long> {

    public boolean existsById(Long id);

    public boolean existsByPersonaId(Long id);

    public Interes getByPersonaId(Long id);
}
