/*
 */
package com.mamoris.portfolio.security.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Federico Mamoris
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreUsuario;
    private String password;

    public Usuario(@NotNull String nombreUsuario, @NotNull String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;

    }
}
