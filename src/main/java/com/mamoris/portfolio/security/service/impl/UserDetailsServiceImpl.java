/*
 */
package com.mamoris.portfolio.security.service.impl;

import com.mamoris.portfolio.entity.Usuario;
import com.mamoris.portfolio.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Federico Mamoris
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    
    private final static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no registrado en la base de datos");
        } else {
            logger.info("Usuario encontrado");
        }
        //Creo usuario con authorities
        return UsuarioGrantedAuthorities.build(usuario);
    }
}
