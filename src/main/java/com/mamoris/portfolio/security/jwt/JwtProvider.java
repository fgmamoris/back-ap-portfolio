/*
 */
package com.mamoris.portfolio.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mamoris.portfolio.security.service.impl.UsuarioGrantedAuthorities;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author Federico Mamoris
 */
@Component
public class JwtProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UsuarioGrantedAuthorities usuarioPrincipal = (UsuarioGrantedAuthorities) authentication.getPrincipal();
        /*return token = JWT.create()
                .withSubject(usuarioPrincipal.getUsername())
                .withExpiresAt(new Date(new Date().getTime() + expiration * 1000))
                .withClaim("roles", usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC512(secret.getBytes()));*/
//response.setHeader("access-token", access_token);
        String token = JWT.create()
                .withSubject(usuarioPrincipal.getUsername())
                .withExpiresAt(new Date(new Date().getTime() + expiration * 1000))
                .withClaim("roles", usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(Algorithm.HMAC512(secret.getBytes()));

        //return "Bearer " + token;
        return token;
    }

    public String getNombreUsuarioFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secret.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decode = jwtVerifier.verify(token);
        return decode.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            //Verifico la identidad del token generado con el secret
            Algorithm algorithm = Algorithm.HMAC512(secret.getBytes());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decode = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            LOGGER.error("Error " + e.getMessage());
        }
        return false;
    }
}
