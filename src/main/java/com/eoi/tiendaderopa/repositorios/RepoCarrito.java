package com.eoi.tiendaderopa.repositorios;


import com.eoi.tiendaderopa.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCarrito extends JpaRepository<Carrito, Long> {
    static String findByTokenSession(String sessionToken) {
        return sessionToken;
    }

        void delete(String car);

        Carrito guardar(String carrito);
}
