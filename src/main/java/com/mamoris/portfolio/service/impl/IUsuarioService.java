package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Persona;
import com.mamoris.portfolio.security.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Federico Mamoris
 */
public interface IUsuarioService {

    public List<Usuario> getAll();

    public Optional<Usuario> getUsuarioById(Long id);

    public Usuario save(Usuario user);

    public void deleteById(Long id);

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario);

    public boolean existsByNombreUsuario(String nombreUsuario);

}
