package com.eoi.tiendaderopa.controladores;

import java.util.UUID;

import ch.qos.logback.core.model.Model;
import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.entidades.ProductoCarrito;
import com.eoi.tiendaderopa.servicios.SrvcCarrito;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Random;

@Controller
public class CarritoCtrl {

    @Autowired
    private SrvcProducto srvcProducto;

    @Autowired
    private SrvcCarrito srvcCarrito;

    @PostMapping("/addToCarrito")
    public String addToCarrito(HttpSession session, Model model, @RequestParam("id") Long id, @RequestParam("cantidad") int cantidad) {
        // sessionToken
        String sessionToken = (String) session.getAttribute("sessionToken");
        if(sessionToken == null) {
            Random rand = new Random();
            sessionToken = UUID.randomUUID().toString();
            session.setAttribute("sessionToken", sessionToken);
            SrvcCarrito.addCarritoPrimeraVez(id, sessionToken, cantidad);
        }
        else {
            srvcCarrito.addToExistingCarrito(id, sessionToken, cantidad);
        }
        return "redirect:/";
    }

    @GetMapping("/carrito")
    public String showViewCarrito(HttpServletRequest request, Model model) {

        return "carrito";
    }

    @PostMapping("/updateProductoCarrito")
    public String updateProductoCarrito(@RequestParam("producto_id") Long id,
                                 @RequestParam("cantidad") int cantidad) {
        srvcCarrito.updateProductoCarrito(id, cantidad);
        return "redirect:carrito";
    }
    @GetMapping("/removeProductoCarrito/{id}")
    public String removeProducto(@PathVariable("id") Long id, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
        System.out.println("elemento eliminado");
        srvcCarrito.removeProductoCarritoFromCarrito(id,sessionToken);
        return "redirect:/carrito";
    }

    @GetMapping("/clearCarrito")
    public String clearCarrito(HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontToken");
        request.getSession(false).removeAttribute("sessiontToken");
        srvcCarrito.clearCarrito(sessionToken);
        return "redirect:/carrito";
    }
}
