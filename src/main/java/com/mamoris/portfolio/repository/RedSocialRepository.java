package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.RedSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface RedSocialRepository extends JpaRepository<RedSocial, Long> {

    public boolean existsById(Long id);
}
