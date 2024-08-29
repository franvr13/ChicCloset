package com.eoi.tiendaderopa.repositorios;

import com.eoi.tiendaderopa.entidades.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoVenta extends JpaRepository<ItemPedido, Integer> {

}