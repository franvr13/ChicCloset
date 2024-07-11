package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pago")

public class Pago implements Serializable{

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name ="metodoPago",length = 45)
    private String metodoPago;

    @Column (name ="fechaPago")
    private LocalDateTime fechaPago;

    @Column (name ="total")
    private Double total;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "facturas_id", foreignKey = @ForeignKey(name = "fk_pago_facturas"))
    private Factura pagoFactura;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "metodoPago_id", foreignKey = @ForeignKey(name = "fk_metodoPago_pago"))
    private MetodoPago metodoPagoPago;

}
