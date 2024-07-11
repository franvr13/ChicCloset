package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")

public class Pedido implements Serializable {

    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column (name ="cantidad")
    private Integer cantidad;

    @Column (name ="precio_unidad")
    private double precio_unidad;

    @Column (name ="fecha_pedido")
    private LocalDateTime fecha_pedido;

    @Column (name ="fecha_entrega")
    private LocalDateTime fecha_entrega;

    @Column (name ="estado",length = 45)
    private String estado;

    @Column (name ="codigo_cliente")
    private Integer codigo_cliente;

    @Column (name ="comentario",length = 255)
    private String comentario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_usuario_pedido"))
    private Usuario usuarioPedido;

    @OneToMany(mappedBy = "pedidoFactura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Factura> facturaPedido;

}
