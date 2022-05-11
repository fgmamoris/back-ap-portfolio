/*
 */
package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Rol;
import com.mamoris.portfolio.repository.RolRepository;
import com.mamoris.portfolio.repository.UsuarioRepository;
import com.mamoris.portfolio.service.impl.IRolService;
import com.mamoris.portfolio.utils.enums.RolNombre;
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
public class RolService implements IRolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolService.class);

    @Autowired
    private RolRepository repo;
    @Autowired
    private UsuarioRepository repoUsuario;

    @Override
    public List<Rol> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Rol> findRolById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<Rol> findRolByUsuario(Long id) {
        return repo.findById(id);
        //return repo.findByIdUsuario(repoUsuario.findById(id));

    }

    @Override
    public Rol findByRolNombre(RolNombre rolNombre) {
        return repo.findByRolNombre(rolNombre);
    }

    @Override
    public Rol save(Rol rol) {
        return repo.save(rol);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
