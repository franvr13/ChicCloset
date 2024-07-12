package com.eoi.tiendaderopa.servicios;
import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrvcPedido extends AbstractBusinessSrvc {
    protected SrvcPedido(RepoPedido repoPedido) {
        super(repoPedido);
    }
}
