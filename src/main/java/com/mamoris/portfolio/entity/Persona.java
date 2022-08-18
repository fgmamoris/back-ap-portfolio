/*
 */
package com.mamoris.portfolio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Federico Mamoris
 */
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "persona")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 128)
    private String nombre;
    @NotNull
    @Size(min = 3, max = 128)
    private String apellido;
    @NotNull
    @Column(nullable = false)
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @Size(min = 5, max = 128)
    private String direccion;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Acerca acerca;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<RedSocial> redesSociales;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Experiencia> experiencias;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Educacion> educacion;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Interes interes;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Skill> skill;

    //@ToString.Exclude
    //@JsonIgnoreProperties("persona")
    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Certificado> certificados;

    public Persona(Long id, String nombre, String apellido, String email, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
    }

}
