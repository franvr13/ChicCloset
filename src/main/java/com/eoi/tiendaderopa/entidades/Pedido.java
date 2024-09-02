package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_pedido")
    private LocalDateTime fecha_pedido;

    @Column(name = "fecha_entrega")
    private LocalDateTime fecha_entrega;

    @Column(name = "estado", length = 45)
    private String estado;

    @Column(name = "codigo_cliente")
    private Integer codigo_cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_usuario_pedido"))
    private Usuario usuarioPedido;

    @OneToMany(mappedBy = "pedidoFactura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Factura> facturaPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedido> items;

}
