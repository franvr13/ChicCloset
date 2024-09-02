package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil")
public class Perfil implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name", length = 50)
    private String name;


    @Size(max = 15, message = "El número de teléfono no puede tener más de 15 carácteres")
    @Column(name = "phone", length = 15)
    private String phone;

    @NotBlank
    @Column(name = "country")
    private Integer country;

    @NotBlank
    @Column(name = "address")
    private Integer address;

    @NotBlank
    @Column(name = "postalcode")
    private Integer postalcode;

    @OneToOne
    @JoinColumn (name= "usuario_id")
    private Usuario usuario;

}