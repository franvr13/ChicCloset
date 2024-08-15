package com.eoi.tiendaderopa.controladores;

import java.util.ArrayList;
import java.util.UUID;


import com.eoi.tiendaderopa.entidades.Carrito;
import com.eoi.tiendaderopa.servicios.SrvcCarrito;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addToCarrito(@RequestParam Long id, @RequestParam int cantidad, HttpSession session) {
        String sessionToken = (String) session.getAttribute("sessionToken");
        if (sessionToken == null) {
            sessionToken = UUID.randomUUID().toString();
            session.setAttribute("sessionToken", sessionToken);
        }

        Carrito carrito = srvcCarrito.getCarritoBySessionToken(sessionToken);
        if (carrito == null) {
            carrito = srvcCarrito.addCarritoPrimeraVez(id, sessionToken, cantidad);
        }

        try {
            srvcCarrito.addToExistingCarrito(id, sessionToken, cantidad, session);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/carrito?token=" + sessionToken;
    }
    @GetMapping("/carrito")
    public String showViewCarrito(HttpSession session, Model model) {
        String sessionToken = (String) session.getAttribute("sessionToken");
        if (sessionToken != null) {
            Carrito carrito = srvcCarrito.getCarritoBySessionToken(sessionToken);
            if (carrito == null) {
                carrito = new Carrito();
                carrito.setTokenSession(sessionToken);
                carrito = srvcCarrito.addCarritoPrimeraVez(null, sessionToken, 0);
            }
            model.addAttribute("carrito", carrito);
            model.addAttribute("productos", carrito.getProducto());
        } else {
            model.addAttribute("carrito", new Carrito());
            model.addAttribute("productos", new ArrayList<>());
        }
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
