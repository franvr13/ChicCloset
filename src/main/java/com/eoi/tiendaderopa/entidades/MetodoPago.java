package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "metodoPago")
public class MetodoPago implements Serializable {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name ="nombre", length = 45)
    private String nombre;

    @Column (name ="pago_externo", length = 45)
    private String pago_externo;

    @OneToMany(mappedBy = "metodoPagoPago", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Pago> pagoMetodoPago;

}
