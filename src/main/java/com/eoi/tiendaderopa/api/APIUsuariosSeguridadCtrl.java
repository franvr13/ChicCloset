package com.eoi.tiendaderopa.api;

import ch.qos.logback.core.model.Model;
import com.eoi.tiendaderopa.entidades.Rol;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoRol;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/usuarios")
public class APIUsuariosSeguridadCtrl {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SrvcUsuario usuarioSrvc;
    private final RepoUsuario repoUsuario;
    private final RepoRol repoRol;

    public APIUsuariosSeguridadCtrl(RepoUsuario repoUsuario, RepoRol repoRol, BCryptPasswordEncoder bCryptPasswordEncoder, SrvcUsuario usuarioSrvc) {
        this.repoUsuario = repoUsuario;
        this.repoRol = repoRol;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioSrvc = usuarioSrvc;
    }

    // Para crear un usuario hay dos bloques:
    // 1. El que genera la pantalla para pedir los datos de tipo GetMapping
    // 2. Cuando pasamos información a la pantalla hay que usar ModelMap

    @GetMapping("/registro")
    public String registro(ModelMap modelMap) {
        final Usuario usuario = new Usuario();
        final List<Rol> roles = repoRol.findAll();
        modelMap.addAttribute("usuario", usuario);
        modelMap.addAttribute("roles", roles);
        System.out.println("Preparando pantalla registro");
        return "crear-usuario";
    }
}
