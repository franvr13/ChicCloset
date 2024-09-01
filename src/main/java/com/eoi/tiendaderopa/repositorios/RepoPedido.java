package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPedido extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuarioPedido_Id(int usuarioId);
}
