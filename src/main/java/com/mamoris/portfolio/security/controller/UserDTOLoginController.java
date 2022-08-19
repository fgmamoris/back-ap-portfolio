/*
 
 */
package com.mamoris.portfolio.security.controller;

import com.mamoris.portfolio.controller.CertificadoController;
import com.mamoris.portfolio.security.entity.UserDTOLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Federico Mamoris
 */
@RestController
@RequestMapping("/api/jwt")
@CrossOrigin(origins = "https://portfolio-fm.web.app/")
public class UserDTOLoginController {

    private final static Logger Log = LoggerFactory.getLogger(CertificadoController.class);

    @PostMapping("/userLogin")

    public UserDTOLogin login(@RequestBody UserDTOLogin userDTOLogin) //public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
    {
        String token = getJWTToken(userDTOLogin.getNombreUsuario());
        UserDTOLogin user = new UserDTOLogin();
        user.setNombreUsuario(userDTOLogin.getNombreUsuario());
        user.setToken(token);
        return user;

    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/get")
    public String test() //public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
    {
        return "Ok";

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
