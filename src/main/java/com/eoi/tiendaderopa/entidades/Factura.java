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
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "importe")
    private Integer importe;

    @Column(name = "fechaFactura")
    private LocalDateTime fechaFactura;

    @Column(name = "fechaVencimiento")
    private LocalDateTime fechaVencimiento;

    @OneToOne(mappedBy = "pagoFactura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Pago pagoFacturas;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "fk_pedido_factura"))
    private Pedido pedidoFactura;

}
