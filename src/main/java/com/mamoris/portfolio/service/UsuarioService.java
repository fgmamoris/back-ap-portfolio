/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.repository.RolRepository;
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

    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return repo.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public void addRoleToUser(String nombreUsuario, String rolNombre) {
        Usuario usuario = repo.findByNombreUsuario(nombreUsuario);

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return repo.getById(id);
    }

    @Override
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        repo.findById(Long.MIN_VALUE);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
