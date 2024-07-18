package com.eoi.tiendaderopa.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("titulo", "Página Principal");
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
