package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    
}
