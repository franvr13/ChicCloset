package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity(name = "deseados")
@Getter
@Setter
public class ListaProductosDeseados {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String sessionToken;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductoDeseado> producto = new HashSet<ProductoDeseado>();

}


