package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    public boolean existsByEmail(String email);
    public boolean existsById(Long id);
}
