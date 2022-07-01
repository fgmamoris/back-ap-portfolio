package com.mamoris.portfolio;

import com.mamoris.portfolio.security.entity.Rol;
import com.mamoris.portfolio.service.RolService;
import com.mamoris.portfolio.utils.enums.RolNombre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioPersonalApplication.class, args);
    }

    CommandLineRunner run(RolService rol) {
        return args -> {
            rol.save(new Rol(1, RolNombre.ROLE_USER));
            rol.save(new Rol(2, RolNombre.ROLE_ADMIN));
        };
    }
}
