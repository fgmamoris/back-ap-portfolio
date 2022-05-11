/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mamoris.portfolio.repository;

import com.mamoris.portfolio.entity.Rol;
import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.utils.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Federico Mamoris
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    public Rol findByRolNombre(RolNombre rolNombre);
    /*
    public Optional<Rol> findByIdUsuario(Optional<Usuario> findById);

    public Rol findByIdNombre(String rolNombre);*/
}
