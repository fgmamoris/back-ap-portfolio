/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mamoris.portfolio.service;

import com.mamoris.portfolio.entity.Rol;
import com.mamoris.portfolio.repository.RolRepository;
import com.mamoris.portfolio.service.impl.IRolService;
import java.util.List;
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

    @Autowired(required=false)
    private RolRepository repo;

    @Override
    public List<Rol> findAll() {
        return repo.findAll();
    }

    @Override
    public Rol findRolById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rol findRolByUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rol findRolByNombre(String rolNombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rol save(Rol rol) {
        return repo.save(rol);
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
