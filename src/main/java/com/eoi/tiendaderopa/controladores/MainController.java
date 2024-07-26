package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
        List<List<Producto>> particiones = partitionList(productos, 3);
        model.addAttribute("particiones", particiones);
        model.addAttribute("productos", productos);

        return "index";
    }



    private <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
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
