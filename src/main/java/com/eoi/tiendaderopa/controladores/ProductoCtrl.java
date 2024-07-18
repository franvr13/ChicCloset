package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.servicios.SrvcPedido;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/productos")
public class ProductoCtrl {

    @Autowired
    private SrvcProducto productoService;
    

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> entidades = productoService.buscarEntidades();
        model.addAttribute("entidades", entidades);
        return "productos";
    }

    // Este parámetro sirve para mostrar un pedido buscándolo por su id
    @GetMapping("/{id}")
    public String mostrarPedido(@PathVariable int id, Model model) {
        Optional<Pedido> pedido = productoService.encuentraPorId(id);
        model.addAttribute("pedidos", pedido);
        return "redirect:/pedidos";
    }


}
