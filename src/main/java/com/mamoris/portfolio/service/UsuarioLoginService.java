package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.security.repository.RolRepository;
import com.mamoris.portfolio.security.repository.UsuarioLoginRepository;
import com.mamoris.portfolio.security.dto.UsuarioLogin;
import com.mamoris.portfolio.service.impl.IUsuarioLoginService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Federico Mamoris
 */
@Service
public class UsuarioLoginService implements IUsuarioLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioLoginService.class);

    @Autowired
    private UsuarioLoginRepository repo;

    @Autowired
    private RolRepository repoRol;

    @Override
    public List<UsuarioLogin> getAll() {
        return repo.findAll();
    }

    @Override
    public UsuarioLogin save(UsuarioLogin user) {
        return repo.save(user);
    }

    @Override
    public void deleteById(Long id) {

        repo.deleteById(id);
    }

    public UsuarioLogin findByNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return repo.existsByNombreUsuario(nombreUsuario);
    }
  

    @Override
    public void addRoleToUser(String nombreUsuario, String rolNombre) {
        UsuarioLogin usuario = repo.findByNombreUsuario(nombreUsuario);

    }

    @Override
    public UsuarioLogin getUsuarioById(Long id) {
        return repo.getById(id);
    }

    @Override
    public Optional<UsuarioLogin> getByNombreUsuario(String nombreUsuario) {
        return repo.findById(Long.MIN_VALUE);
    }

}
