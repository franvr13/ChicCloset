package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.servicios.SrvcBusqueda;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import com.eoi.tiendaderopa.servicios.SrvcDetallesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioCtrl {

    final SrvcUsuario usuarioSrvc;
    final SrvcBusqueda busquedaSrvc;
    final SrvcDetallesUsuario usuarioDetallesSrvc;

    public UsuarioCtrl(SrvcUsuario usuarioSrvc, SrvcBusqueda busquedaSrvc, SrvcDetallesUsuario usuarioDetallesSrvc) {
        this.usuarioSrvc = usuarioSrvc;
        this.busquedaSrvc = busquedaSrvc;
        this.usuarioDetallesSrvc = usuarioDetallesSrvc;
    }

    @GetMapping("")
    public String mostrarListaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioSrvc.buscarEntidades());
        return "usuarios";
    }


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioSrvc.registrarUsuario(usuario);
        return "/registrosatisfactorio";
    }

    @GetMapping("/detalles/{idUsuario}")
    public String mostrarDetallesUsuario(@ModelAttribute DetallesUsuario detalle, @PathVariable int idUsuario, Model model) {

        model.addAttribute("detalles", usuarioDetallesSrvc.obtenerDetallesUsuarioporId(idUsuario));
        return "detallesUsuario";
    }

    @PostMapping("/detalles/{idUsuario}")
    public String guardarDetallesUsuario(@ModelAttribute DetallesUsuario detalle, @PathVariable int idUsuario) {
        detalle.setUsuario(usuarioSrvc.encuentraPorId(idUsuario).get());
        try {
            usuarioDetallesSrvc.guardar(detalle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


