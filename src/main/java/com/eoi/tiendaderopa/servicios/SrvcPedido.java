package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrvcPedido extends AbstractBusinessSrvc {
    private final RepoPedido repoPedido;

    @Autowired
    public SrvcPedido(RepoPedido repoPedido) {
        super(repoPedido);
        this.repoPedido = repoPedido;
    }

    public List<Pedido> findPedidosByUsuarioId(int usuarioId) {
        return repoPedido.findByUsuarioPedido_Id(usuarioId); // Usa el método con relación
    }
}
