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




@Controller
public class UsuarioCtrl {

    @Autowired
    SrvcUsuario usuarioSrv;

    @Autowired
    SrvcBusquedas busquedaSrv;

    @Autowired
    SrvcUsuarioDetalles usuarioDetallesSrv;

    @GetMapping("/usuario/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }
    
    @PostMapping("/usuario/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioSrv.registrarUsuario(usuario);
        return "/registrosatisfactorio";
    }
    @GetMapping("/usuario/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @GetMapping("/detalles")
    public String mostrarDetallesUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        model.addAttribute("detalles",usuarioDetallesSrv.obtenerDetallesUsuario(usuario));
        return "detallesUsuario";
    }

    @PostMapping("/detalles/{idUsuario}")
    public String guardarDetallesUsuario(@ModelAttribute DetallesUsuario detalle, @PathVariable int idUsuario) {
        usuarioSrv.guardarDetallesUsuario(idUsuario, detalle);
        return "detallesUsuario";
    }

    @PostMapping("/busqueda")
    public String buscar(@RequestParam String termino, @ModelAttribute("usuario") Usuario usuario) {
        busquedaSrv.GuardarBusqueda(termino, usuario);
        return "resultadosBusqueda";
    }

    @GetMapping("/historial")
    public String verHistorial(@ModelAttribute("usuario") Usuario usuario, Model model) {
        model.addAttribute("historial", busquedaSrv.obtenerHistorialBusquedas(usuario));
        return "historialBusquedas";
    }

    @DeleteMapping("/cuenta/{id}")
    public String eliminarUsuario(@ModelAttribute("usuario") Usuario usuario, Integer idUsuario) {
        usuarioSrv.eliminarPorId(idUsuario);
        return "redirect:/home";
    }
}


