package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SrvcProducto extends AbstractBusinessSrvc {
    private final RepoProducto repoProducto;

    protected SrvcProducto(RepoProducto repo, RepoProducto repoProducto) {
        super(repo);
        this.repoProducto = repoProducto;
    }


    public Page<Producto> obtenerProductosPaginados(int pagina, int tamaño) {
        return repoProducto.findAll(PageRequest.of(pagina, tamaño));
    }

    public Page<Producto> obtenerProductosFiltradosPaginados(String color, String talla, int pagina, int tamano) {
        if (color != null && !color.isEmpty() && talla != null && !talla.isEmpty()) {
            return repoProducto.findByColorAndTalla(color, talla, PageRequest.of(pagina, tamano));
        } else if (color != null && !color.isEmpty()) {
            return repoProducto.findByColor(color, PageRequest.of(pagina, tamano));
        } else if (talla != null && !talla.isEmpty()) {
            return repoProducto.findByTalla(talla, PageRequest.of(pagina, tamano));
        } else {
            return repoProducto.findAll(PageRequest.of(pagina, tamano));
        }
    }

    public List<String> obtenerColoresDisponibles() {
        return repoProducto.findAll().stream()
                .map(Producto::getColor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> obtenerTallasDisponibles() {
        return repoProducto.findAll().stream()
                .map(Producto::getTalla)
                .distinct()
                .collect(Collectors.toList());
    }
}
