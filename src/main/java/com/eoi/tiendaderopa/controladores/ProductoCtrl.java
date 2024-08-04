package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/productos")
public class ProductoCtrl {

    @Autowired
    private SrvcProducto productoService;
    @Autowired
    private RepoProducto repoProducto;


    // Este parámetro sirve para mostrar una lista de productos
   /* @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.buscarEntidades();
        model.addAttribute("productos", productos);
        return "productos";
    }*/

   /* @GetMapping("")
    public String listarProductos(Model model, @RequestParam (defaultValue = "0") int pagina) {
        int tamano = 12;
        Page<Producto> paginaProductos = productoService.obtenerProductosPaginados(pagina, tamano);
        model.addAttribute("paginaProductos", paginaProductos);
        model.addAttribute("numeroPaginaActual", pagina);
        return "productos";
    }*/

    // Este parámetro sirve para mostrar una lista de los pedidos
    @GetMapping("/{id}")
    public String verProducto(Model model, @PathVariable long id) {
        Optional<Producto> producto = productoService.encuentraPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "detallesproducto";
        } else {
            // Manejo del caso en el que no se encuentra el producto (opcional)
            return "redirect:/productos";
        }

    }

    @GetMapping("")
    public String listarProductos(Model model, @RequestParam(defaultValue = "0") int pagina,
                                  @RequestParam(required = false) String color,
                                  @RequestParam(required = false) String talla) {
        int tamano = 12;
        Page<Producto> paginaProductos = productoService.obtenerProductosFiltradosPaginados(color, talla, pagina, tamano);

        List<String> colores = productoService.obtenerColoresDisponibles();
        List<String> tallas = productoService.obtenerTallasDisponibles();
        model.addAttribute("paginaProductos", paginaProductos);
        model.addAttribute("numeroPaginaActual", pagina);
        model.addAttribute("colores", colores);
        model.addAttribute("colorSeleccionado", color);
        model.addAttribute("tallas", tallas);
        model.addAttribute("tallaSeleccionada", talla);

        return "productos";
    }


}
