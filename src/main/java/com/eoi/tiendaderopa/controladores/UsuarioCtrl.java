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

    @GetMapping("/detalles/{idUsuario}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String mostrarDetallesUsuario(@ModelAttribute DetallesUsuario detalle, @PathVariable int idUsuario,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         Model model
                                         ) {
        model.addAttribute("usuario", usuarioSrvc.getRepo().findById(idUsuario).get());
        Optional<DetallesUsuario> detallesUsuario = usuarioDetallesSrvc.obtenerDetallesUsuario(usuarioSrvc.getRepo().findById(idUsuario).get());
        if(detallesUsuario.isPresent())
        {
            model.addAttribute("detalles", detallesUsuario.get());
        }
        else {
            DetallesUsuario detallesUsuario1 = new DetallesUsuario();
            model.addAttribute("detalles", detallesUsuario1);
        }
        return "detallesUsuario";
    }



    @PostMapping("/detalles/{idUsuario}")
    public String guardarDetallesUsuario(@ModelAttribute DetallesUsuario detalle,
                                         @PathVariable int idUsuario,
                                         Model model) {
        detalle.setUsuario(usuarioSrvc.encuentraPorId(idUsuario).get());
        try { detalle = usuarioDetallesSrvc.guardar(detalle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("detalles", detalle);
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


