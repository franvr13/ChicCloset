package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPedido extends JpaRepository<Pedido, Integer> {

}
