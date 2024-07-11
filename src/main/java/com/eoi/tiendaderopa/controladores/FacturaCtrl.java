package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Factura;
import com.eoi.tiendaderopa.servicios.SrvcFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FacturaCtrl {

    @Autowired
    private SrvcFactura facturaSrvc;

    // Este parámetro sirve para mostrar una lista de las facturas
    @GetMapping("/facturas")
    public String listarFacturas(Model model) {
        List<Factura> facturas = facturaSrvc.buscarEntidades();
        model.addAttribute("facturas", facturas);
        return "facturas";
    }

    // Este parámetro sirve para mostrar una factura buscándola por su id
    @GetMapping("/facturas/{id}")
    public String mostrarFactura(@PathVariable int id, Model model) {
        Optional<Factura> factura = facturaSrvc.encuentraPorId(id);
        model.addAttribute("factura", factura);
        return "redirect:/facturas";
    }

    // Este parámetro sirve para crear una nueva factura
    @PostMapping("/facturas")
    public String crearFactura(@RequestBody Factura factura, Model model) {
        try {
            facturaSrvc.guardar(factura);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/facturas";
    }

    // Este parámetro sirve para eliminar una factura
    @DeleteMapping("/facturas/{id}")
    public String eliminarFactura(@PathVariable int id, Model model) {
        facturaSrvc.eliminarPorId(id);
        return "redirect:/facturas";
    }

}
