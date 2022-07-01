/*
 *
 */
package com.mamoris.portfolio.security.service.impl;

import com.mamoris.portfolio.security.dto.UsuarioLogin;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Federico Mamoris
 */
public class UsuarioGrantedAuthorities implements UserDetails {

    private String nombreUsuario;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioGrantedAuthorities(String nombreUsuario, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.authorities = authorities;
    }

    //Cargo el usuario con los granted authotities
    public static UsuarioGrantedAuthorities build(UsuarioLogin usuario) {
        List<GrantedAuthority> authorities
                = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioGrantedAuthorities(usuario.getNombreUsuario(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
