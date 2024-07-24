package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("/{id}")
    public String verProducto(Model model, @PathVariable long id) {
        Optional<Producto> producto= productoService.encuentraPorId(id);
        if(producto.isPresent())
        {
            model.addAttribute("producto", producto.get());
        }
        return "detallesProducto";
    }


}
