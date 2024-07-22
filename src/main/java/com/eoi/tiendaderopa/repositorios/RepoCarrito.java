package com.eoi.tiendaderopa.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eoi.tiendaderopa.entidades.Carrito;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCarrito extends JpaRepository<Carrito, Integer> {

    List<Carrito> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

}