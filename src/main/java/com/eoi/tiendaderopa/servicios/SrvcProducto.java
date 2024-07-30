package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
