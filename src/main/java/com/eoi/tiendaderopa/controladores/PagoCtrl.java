package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Pago;
import com.eoi.tiendaderopa.servicios.SrvcPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/pago")
public class PagoCtrl {

    @Autowired
    private SrvcPago pagoSrvc;



    @GetMapping("/enviarinfo")
    public String mostrarFormularioEnvio() {
        return "enviarinfo";
    }

    @PostMapping("/datosPago")
    public String mostrarFormularioPago(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("tel") String tel,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            Model model) {

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("tel", tel);
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        model.addAttribute("address", address);

        return "pago";
    }

    @PostMapping("/procesarPago")
    public String procesarPago(
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("cardHolder") String cardHolder,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cvv") String cvv,
            Model model) {

        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cardHolder", cardHolder);
        model.addAttribute("expiryDate", expiryDate);
        model.addAttribute("cvv", cvv);

        return "pagoexito";
    }



    // Este parámetro sirve para mostrar una lista de pagos
    @GetMapping("/lista")
    public String listarPagos(Model model) {

        List<Pago> pago = pagoSrvc.buscarEntidades();
        model.addAttribute("pagos", pago);
        model.addAttribute("titulo", "Pagos");
        return "pagos";
    }

    // Este parámetro sirve para crear un nuevo pago
    @PostMapping("/nuevo")
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
