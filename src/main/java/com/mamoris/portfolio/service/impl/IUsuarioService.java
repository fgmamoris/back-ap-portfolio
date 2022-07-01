package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Federico Mamoris
 */
public interface IUsuarioService {

    public List<Usuario> getAll();

    public Usuario save(Usuario user);

    public Usuario getUsuarioById(Long id);

    public void deleteById(Long id);

}
