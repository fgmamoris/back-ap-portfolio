package com.mamoris.portfolio.security.controller;

import com.mamoris.portfolio.entity.Rol;
import com.mamoris.portfolio.security.dto.JwtDto;
import com.mamoris.portfolio.security.dto.UsuarioLogin;
import com.mamoris.portfolio.security.jwt.JwtProvider;
import com.mamoris.portfolio.service.impl.IRolService;
import com.mamoris.portfolio.service.impl.IUsuarioLoginService;
import com.mamoris.portfolio.utils.Mensaje;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUsuarioLoginService usuarioLoginService;

    @Autowired
    IRolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@RequestBody UsuarioLogin nuevo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioLoginService.getAll().size() == 0) {

            /*if (usuarioService.existsByNombreUsuario(nuevo.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevo.getEmail())) {
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }*/
            Collection<Rol> roles = new ArrayList<>();
            /*for (Rol role : nuevo.getRoles()) {
            roles.add(rolService.findByRolNombre(role.getRolNombre()));
           
        }*/
            for (Rol role : nuevo.getRoles()) {
                roles.add(rolService.findByRolNombre(role.getRolNombre()));
            }
            //usuario.setRoles(nuevo.getRoles().stream().map(rol -> rolService.findByRolNombre(rol.getRolNombre())).collect(Collectors.toList()));

            UsuarioLogin usuario
                    = new UsuarioLogin(nuevo.getNombreUsuario(), passwordEncoder.encode(nuevo.getPassword()), nuevo.getRoles());
            usuarioLoginService.save(usuario);
            System.out.println(usuario.toString());
            return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(new Mensaje("Comunicarse con el administrador del sistema"), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UsuarioLogin loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
