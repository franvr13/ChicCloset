package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.HistorialBusquedas;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoBusquedas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SrvcBusquedas extends AbstractBusinessSrvc<HistorialBusquedas, Integer, RepoBusquedas> {

    @Autowired
    public SrvcBusquedas(RepoBusquedas repo) {
        super(repo);
    }

    @Autowired
    RepoBusquedas repoBusquedas;

    public void GuardarBusqueda(String terminobusqueda, Usuario usuario) {
        HistorialBusquedas busqueda = new HistorialBusquedas();
        busqueda.setUsuario(usuario);
        busqueda.setBusqueda(terminobusqueda);
        repoBusquedas.save(busqueda);
    }

    public List<HistorialBusquedas> obtenerHistorialBusquedas(Usuario usuario) {
        return repoBusquedas.findByUsuario(usuario);
    }

    //hace falta la entidad de las caracteristicas de producto
   /* public List<DetallesProducto> buscar(String termino) {
        return productos.stream()
                .filter(detalle -> detalle.toLowerCase().contains(termino.toLowerCase()))
                .collect(Collectors.toList());
    }*/
}
