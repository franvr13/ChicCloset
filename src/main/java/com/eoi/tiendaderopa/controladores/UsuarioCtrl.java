package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.servicios.SrvcBusqueda;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import com.eoi.tiendaderopa.servicios.SrvcDetallesUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
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

    @GetMapping("")
    public String mostrarListaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioSrvc.buscarEntidades());
        return "usuarios";
    }


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(@Valid Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
        // Validar que los campos obligatorios no estén vacíos
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            model.addAttribute("error", "El email es obligatorio.");
            return "registro";
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            model.addAttribute("error", "La contraseña es obligatoria.");
            return "registro";
        }

        // Validar la fortaleza de la contraseña
        if (!isValidPassword(usuario.getContraseña())) {
            model.addAttribute("error", "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, una minúscula, un número y un carácter especial.");
            return "registro";
        }

        // Si las validaciones pasan, registrar el usuario
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

    // Método privado para validar la fortaleza de la contraseña
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;:,.<>?".indexOf(ch) >= 0);

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
}


