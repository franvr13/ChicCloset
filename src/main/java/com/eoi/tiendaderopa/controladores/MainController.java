package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private final RepoProducto repoProducto;

    public MainController(RepoProducto repoProducto) {
        this.repoProducto = repoProducto;
    }


    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("titulo", "Página Principal");
        List<Producto> productos = repoProducto.findAll();
        model.addAttribute("productos", productos);
        return "index";
    }

    @GetMapping("/carrito")
    public String carrito(Model model) {
        return "carrito";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/tienda")
    public String tienda(Model model) {
        return "tienda";
    }


    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }


}
