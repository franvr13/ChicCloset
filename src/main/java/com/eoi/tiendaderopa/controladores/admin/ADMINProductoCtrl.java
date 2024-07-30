package com.eoi.tiendaderopa.controladores.admin;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/productos")
public class ADMINProductoCtrl {

    private final SrvcProducto productoService;

    public ADMINProductoCtrl(SrvcProducto productoService) {
        this.productoService = productoService;
    }

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> entidades = productoService.buscarEntidades();
        model.addAttribute("entidades", entidades);
        return "admin/productos";
    }

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("/{id}")
    public String verProducto(Model model, @PathVariable long id) {
        Optional<Producto> producto = productoService.encuentraPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
        }
        return "admin/detallesproducto";
    }


}
