package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
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
@Table(name = "subcategoria")

public class SubCategoria implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "fk_categoria_id"))
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", foreignKey = @ForeignKey(name = "fk_producto_id"))
    private Producto producto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_devoluciones_id", foreignKey = @ForeignKey(name = "fk_producto_devoluciones_id"))
    private Producto producto_devoluciones;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_devoluciones_pedidos_id", foreignKey = @ForeignKey(name = "fk_producto_devoluciones_pedidos_id"))
    private Producto producto_devoluciones_pedidos;

}
