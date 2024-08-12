package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.servicios.SrvcDeseados;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class DeseadosCtrl {


    private final SrvcDeseados srvcDeseados;

    public DeseadosCtrl(SrvcDeseados srvcDeseados) {
        this.srvcDeseados = srvcDeseados;
    }

    @GetMapping("/addToDeseados/{id}")
    public String addToDeseados(@PathVariable("id") Long id, HttpServletRequest request) {
        // sessiontToken
        String sessionToken = (String) request.getSession(true).getAttribute("sessiontTokenDeseados");
        if (sessionToken == null) {

            sessionToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("sessiontTokenDeseados", sessionToken);
            srvcDeseados.addToDeseadosPrimeraVez(id, sessionToken);
        } else {
            srvcDeseados.addToExistingDeseados(id, sessionToken);
        }

        return "redirect:/";
    }
    @GetMapping("/removeProductoDeseados/{id}")
    public String removeProducto(@PathVariable("id") Long id, HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontTokenDeseados");
        System.out.println("got here ");
        srvcDeseados.removeProductoDeseados(id, sessionToken);
        return "redirect:/carrito";
    }

    @GetMapping("/clearDeseados")
    public String clearCarritoString(HttpServletRequest request) {
        String sessionToken = (String) request.getSession(false).getAttribute("sessiontTokenDeseados");
        request.getSession(false).removeAttribute("sessiontTokenDeseados");
        srvcDeseados.clearDeseados(sessionToken);
        return "redirect:/carrito";
    }
}
