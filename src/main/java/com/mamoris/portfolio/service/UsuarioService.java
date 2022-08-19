package com.mamoris.portfolio.service;

import com.mamoris.portfolio.security.entity.UserDTOLogin;
import com.mamoris.portfolio.security.entity.Usuario;
import com.mamoris.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamoris.portfolio.service.impl.IUsuarioService;

/**
 *
 * @author Federico Mamoris
 */
@Service
public class UsuarioService implements IUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Usuario save(Usuario user) {
        return repo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return repo.existsByNombreUsuario(nombreUsuario);
    }

}
