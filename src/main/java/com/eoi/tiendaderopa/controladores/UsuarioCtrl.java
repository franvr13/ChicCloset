package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.servicios.SrvcBusquedas;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import com.eoi.tiendaderopa.servicios.SrvcUsuarioDetalles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsuarioCtrl {

    @Autowired
    SrvcUsuario usuarioSrvc;

    @Autowired
    SrvcBusquedas busquedaSrvc;

    @Autowired
    SrvcUsuarioDetalles usuarioDetallesSrvc;

    @GetMapping("/usuario/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
    
    @PostMapping("/usuario/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioSrvc.registrarUsuario(usuario);
        return "/registrosatisfactorio";
    }
    @GetMapping("/usuario/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @GetMapping("/detalles")
    public String mostrarDetallesUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        model.addAttribute("detalles",usuarioDetallesSrvc.obtenerDetallesUsuario(usuario));
        return "detallesUsuario";
    }

    @PostMapping("/detalles/{idUsuario}")
    public String guardarDetallesUsuario(@ModelAttribute DetallesUsuario detalle, @PathVariable int idUsuario) {
        usuarioSrvc.guardarDetallesUsuario(idUsuario, detalle);
        return "detallesUsuario";
    }


    @PostMapping("/busqueda/{idUsuario}")
    public String guardarBusqueda(@RequestParam String termino, @PathVariable int idUsuario, Model model) {
        busquedaSrvc.GuardarBusqueda(termino, usuarioSrvc.obtenerPorIdNoOp(idUsuario));
        model.addAttribute("termino", termino);

        //hace falta saber como va a ser la entidad de los resultados
        //List resultados = busquedaSrvc.buscar(termino);
       // model.addAttribute("resultados", resultados);
        return "resultadosBusqueda";
    }

    @GetMapping("/historial/{idUsuario}")
    public String verHistorial(@ModelAttribute("usuario") Usuario usuario, Model model, @PathVariable int idUsuario) {
        model.addAttribute("historial", busquedaSrvc.obtenerHistorialBusquedas(usuarioSrvc.obtenerPorIdNoOp(idUsuario)));
        return "historialBusquedas";
    }

    @DeleteMapping("/cuenta/{id}")
    public String eliminarUsuario(@ModelAttribute("usuario") Usuario usuario, Integer idUsuario) {
        usuarioSrvc.eliminarPorId(idUsuario);
        return "redirect:/home";
    }
}


