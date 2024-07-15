package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.servicios.SrvcBusquedas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusquedaCtrl {

    @Autowired
    SrvcBusquedas srvcBusquedas;

    //modificar
    @GetMapping("/busqueda")
    public String mostrarBusqueda(@RequestParam String termino, Model model) {
        return "Busqueda";
    }
}
