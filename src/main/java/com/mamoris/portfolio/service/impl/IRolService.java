/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mamoris.portfolio.service.impl;

import com.mamoris.portfolio.entity.Rol;
import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.utils.enums.RolNombre;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Federico Mamoris
 */
public interface IRolService {

    public List<Rol> findAll();

    public Optional<Rol> findRolById(Long id);

    public Optional<Rol> findRolByUsuario(Long id);

    public Rol findByRolNombre(RolNombre rolNombre);

    public Rol save(Rol rol);

    public void deleteById(Long id);

}
