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


@Controller
public class CarritoCtrl {

    @Autowired
    private SrvcProducto srvcProducto;

    @Autowired
    private SrvcCarrito srvcCarrito;

    @PostMapping("/addToCarrito")
    public String addToCarrito(HttpSession session, @RequestParam("id") Long id, @RequestParam("cantidad") int cantidad) {
        String sessionToken = (String) session.getAttribute("sessionToken");

        if (sessionToken == null) {
            sessionToken = UUID.randomUUID().toString();
            session.setAttribute("sessionToken", sessionToken);
            srvcCarrito.addCarritoPrimeraVez(id, sessionToken, cantidad);
        } else {
            srvcCarrito.addToExistingCarrito(id, sessionToken, cantidad);
        }
        return "redirect:/";
    }

    @GetMapping("/carrito")
    public String showViewCarrito(HttpSession session, Model model) {
        String sessionToken = (String) session.getAttribute("sessionToken");
        if (sessionToken != null) {
            Carrito carrito = srvcCarrito.getCarritoBySessionToken(sessionToken);
        }
        return "carrito";
    }

    @PostMapping("/updateProductoCarrito")
    public String updateProductoCarrito(@RequestParam("producto_id") Long id, @RequestParam("cantidad") int cantidad) {
        srvcCarrito.updateProductoCarrito(id, cantidad);
        return "redirect:/carrito";
    }

    @GetMapping("/removeProductoCarrito/{id}")
    public String removeProducto(@PathVariable("id") Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionToken = (String) session.getAttribute("sessionToken");
            if (sessionToken != null) {
                srvcCarrito.removeProductoCarritoFromCarrito(id, sessionToken);
            }
        }
        return "redirect:/carrito";
    }

    @GetMapping("/clearCarrito")
    public String clearCarrito(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String sessionToken = (String) session.getAttribute("sessionToken");
            if (sessionToken != null) {
                srvcCarrito.clearCarrito(sessionToken);
                session.removeAttribute("sessionToken");
            }
        }
        return "redirect:/carrito";
    }
}
