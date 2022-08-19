/*
 */
package com.mamoris.portfolio.security.entity;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTOLogin {

    @NotNull
    private String nombreUsuario;

    @NotNull
    private String password;

    @NotNull
    private String token;

}
