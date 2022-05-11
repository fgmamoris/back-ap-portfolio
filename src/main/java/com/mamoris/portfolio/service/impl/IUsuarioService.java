/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public Usuario getUsuarioById(Long id);

    public Usuario save(Usuario user);

    public void deleteById(Long id);

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario);

    public boolean existsByNombreUsuario(String nombreUsuario);

    public boolean existsByEmail(String email);

    public void addRoleToUser(String nombreUsuario, String rol);

}
