package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.servicios.SrvcBusqueda;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import com.eoi.tiendaderopa.servicios.SrvcDetallesUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
@EnableWebSecurity
public class UsuarioCtrl {

    private final SrvcUsuario usuarioSrvc;
    private final  SrvcBusqueda busquedaSrvc;
    private final SrvcDetallesUsuario usuarioDetallesSrvc;

    @Autowired
    public UsuarioCtrl(SrvcUsuario usuarioSrvc, SrvcBusqueda busquedaSrvc, SrvcDetallesUsuario usuarioDetallesSrvc) {
        this.usuarioSrvc = usuarioSrvc;
        this.busquedaSrvc = busquedaSrvc;
        this.usuarioDetallesSrvc = usuarioDetallesSrvc;
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(@Valid Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario( @ModelAttribute("usuario")  @Valid Usuario usuario) {
        usuarioSrvc.registrarUsuario(usuario);
        return "registrosatisfactorio";
    }

    @GetMapping("/perfil")
    public String redirigirADetallesUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        Usuario usuario = usuarioSrvc.obtenerPorEmail(email);
        return "redirect:/usuarios/detalles/" + usuario.getId();
    }

    @GetMapping("/detalles/{idUsuario}")
    public String mostrarDetallesUsuario(@PathVariable int idUsuario, Model model) {
        Optional<Usuario> usuarioOptional = usuarioSrvc.encuentraPorId(idUsuario);
            Usuario usuario = usuarioOptional.get();
            model.addAttribute("usuario", usuario);

            Optional<DetallesUsuario> detallesOptional = usuarioDetallesSrvc.obtenerDetallesUsuario(usuario);
            if (detallesOptional.isPresent()) {
                model.addAttribute("detalles", detallesOptional.get());
            } else {
                DetallesUsuario detallesUsuario = new DetallesUsuario();
                detallesUsuario.setUsuario(usuario);
                model.addAttribute("detalles", detallesUsuario);
            }
            return "detallesUsuario";
    }

    @PostMapping("/detalles/{idUsuario}")
    public String guardarDetallesUsuario(@PathVariable int idUsuario,
                                         @ModelAttribute DetallesUsuario detalles,
                                         Model model) {
        Usuario usuario = usuarioSrvc.encuentraPorId(idUsuario).get();
        detalles.setUsuario(usuario);

        try {
            usuarioDetallesSrvc.guardar(detalles);
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar los detalles del usuario");
            model.addAttribute("usuario", usuario);
            return "detallesUsuario";
        }

        return "redirect:/usuarios/detalles/" + idUsuario;
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


