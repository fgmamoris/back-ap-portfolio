package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.security.repository.RolRepository;
import com.mamoris.portfolio.repository.UsuarioRepository;
import com.mamoris.portfolio.service.impl.IUsuarioService;
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
public class UsuarioService implements IUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RolRepository repoRol;

    @Override
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @Override
    public Usuario save(Usuario user) {

        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public void deleteById(Long id) {

        repo.deleteById(id);
    }

   
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return repo.getById(id);
    }

}
