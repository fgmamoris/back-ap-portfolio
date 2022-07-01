package com.mamoris.portfolio.repository;


import com.mamoris.portfolio.security.dto.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {

    public UsuarioLogin findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);
}
