package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.MetodoPago;
import com.eoi.tiendaderopa.servicios.SrvcMetodoPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MetodoPagoCtrl {

    @Autowired
    private SrvcMetodoPago metodopagoSrvc;

    // Este parámetro sirve para mostrar una lista de métodos de pago
    @GetMapping("/metodopago")
    public String listarMetodoPago(Model model) {
        List<MetodoPago> metodoPago = metodopagoSrvc.buscarEntidades();
        model.addAttribute("metodoPago", metodoPago);
        return "metodo-pago";
    }

    // Este parámetro sirve para crear un nuevo método de mago
    @PostMapping("/metodopago")
    public String crearMetodoPago(@RequestBody MetodoPago metodoPago, Model model) {
        try {
            metodopagoSrvc.guardar(metodoPago);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/metodo-pago";
    }

    // Este parámetro sirve para eliminar un método de pago
    @DeleteMapping("/metodopago/{id}")
    public String eliminarMetodoPago(@PathVariable int id, Model model) {
        metodopagoSrvc.eliminarPorId(id);
        return "redirect:/metodo-pago";
    }

}
