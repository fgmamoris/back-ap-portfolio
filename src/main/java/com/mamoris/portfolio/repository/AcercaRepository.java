package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Acerca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface AcercaRepository extends JpaRepository<Acerca, Long> {

    public boolean existsById(Long id);

    public boolean existsByPersonaId(Long id);

    public Acerca getByPersonaId(Long id);
}
