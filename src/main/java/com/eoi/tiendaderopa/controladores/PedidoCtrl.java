package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.servicios.SrvcPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PedidoCtrl {

    @Autowired
    private SrvcPedido pedidoSrvc;

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("/pedidos")
    public String listarPedido(Model model) {
        List<Pedido> pedido = pedidoSrvc.buscarEntidades();
        model.addAttribute("pedidos", pedido);
        return "pedidos";
    }

    // Este parámetro sirve para mostrar un pedido buscándolo por su id
    @GetMapping("/pedidos/{id}")
    public String mostrarPedido(@PathVariable int id, Model model) {
        Optional<Pedido> pedido = pedidoSrvc.encuentraPorId(id);
        model.addAttribute("pedidos", pedido);
        return "redirect:/pedidos";
    }

    // Este parámetro sirve para crear un nuevo pedido
    @PostMapping("/pedidos")
    public String crearFactura(@RequestBody Pedido pedido, Model model) {
        try {
            pedidoSrvc.guardar(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/pedidos";
    }

    // Este parámetro sirve para eliminar un pedido
    @DeleteMapping("/pedidos/{id}")
    public String eliminarPedido(@PathVariable int id, Model model) {
        pedidoSrvc.eliminarPorId(id);
        return "redirect:/pedidos";
    }

}
