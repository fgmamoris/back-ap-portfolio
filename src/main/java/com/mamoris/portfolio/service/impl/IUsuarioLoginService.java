package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.security.dto.UsuarioLogin;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Federico Mamoris
 */
public interface IUsuarioLoginService {

    public List<UsuarioLogin> getAll();

    public UsuarioLogin getUsuarioById(Long id);

    public UsuarioLogin save(UsuarioLogin user);

    public void deleteById(Long id);

    public Optional<UsuarioLogin> getByNombreUsuario(String nombreUsuario);

    public boolean existsByNombreUsuario(String nombreUsuario);

    public boolean existsByEmail(String email);

    public void addRoleToUser(String nombreUsuario, String rol);

}
