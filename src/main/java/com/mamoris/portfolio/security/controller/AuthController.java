package com.mamoris.portfolio.security.controller;

import com.mamoris.portfolio.security.entity.UserDTOLogin;
import com.mamoris.portfolio.security.entity.Usuario;
import com.mamoris.portfolio.utils.Mensaje;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mamoris.portfolio.service.impl.IUsuarioService;
import com.mamoris.portfolio.utils.GenerateToken;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://portfolio-fm.web.app/")
public class AuthController {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManagerBean();
    }
    @Autowired
    AuthenticationManager authenticationManager;*/
    @Autowired
    IUsuarioService usuarioLoginService;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@RequestBody Usuario nuevo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioLoginService.getAll().isEmpty()) {

            /*if (usuarioService.existsByNombreUsuario(nuevo.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevo.getEmail())) {
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }*/
            //usuario.setRoles(nuevo.getRoles().stream().map(rol -> rolService.findByRolNombre(rol.getRolNombre())).collect(Collectors.toList()));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            Usuario usuario
                    = new Usuario(nuevo.getNombreUsuario(), passwordEncoder().encode(nuevo.getPassword()));

            usuarioLoginService.save(usuario);
            return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(new Mensaje("Comunicarse con el administrador del sistema"), HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTOLogin> login(@Valid @RequestBody Usuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        } else if (!usuarioLoginService.existsByNombreUsuario(loginUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Nombre de usuario incorrecto"), HttpStatus.BAD_REQUEST);
        } else if (!passwordEncoder().matches(loginUsuario.getPassword(), usuarioLoginService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get().getPassword())) {
            return new ResponseEntity(new Mensaje("Contraseña Incorrecta"), HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioLoginService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get();
        UserDTOLogin usuarioDTO = new UserDTOLogin(usuario.getId(), loginUsuario.getNombreUsuario(), GenerateToken.getJWTToken(loginUsuario.getNombreUsuario()));
        return new ResponseEntity(usuarioDTO, HttpStatus.OK);
    }

}
