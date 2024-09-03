package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class DetallesUsuario implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "fullname", length = 50)
    private String fullname;


    @Size(max = 15, message = "El número de teléfono no puede tener más de 15 carácteres")
    @Column(name = "phonenumber", length = 15)
    private String phonenumber;

    @Column(name = "country")
    private Integer country;

    private String ciudad;

    @NotBlank
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "zipcode")
    private Integer zipcode;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
