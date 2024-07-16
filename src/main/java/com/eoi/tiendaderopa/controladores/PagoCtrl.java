package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pago;
import com.eoi.tiendaderopa.servicios.SrvcPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PagoCtrl {

    @Autowired
    private SrvcPago pagoSrvc;

    // Este parámetro sirve para mostrar una lista de pagos
    @GetMapping("/pagos")
    public String listarPagos(Model model) {
        List<Pago> pago = pagoSrvc.buscarEntidades();
        model.addAttribute("pagos", pago);
        return "pagos";
    }

    // Este parámetro sirve para crear un nuevo pago
    @PostMapping("/pagos")
    public String crearPago(@RequestBody Pago pago, Model model) {
        try {
            pagoSrvc.guardar(pago);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/pagos";
    }

    // Este parámetro sirve para eliminar un método de pago
    @DeleteMapping("/pagos/{id}")
    public String eliminarPago(@PathVariable int id, Model model) {
        pagoSrvc.eliminarPorId(id);
        return "redirect:/pagos";
    }

}
