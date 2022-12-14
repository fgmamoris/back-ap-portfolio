package com.mamoris.portfolio.security.repository;

import com.mamoris.portfolio.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface UsuarioLoginRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

}
