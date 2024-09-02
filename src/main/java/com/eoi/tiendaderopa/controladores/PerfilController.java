package com.eoi.tiendaderopa.controladores;

import ch.qos.logback.core.model.Model;
import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.repositorios.RepoDetallesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    private final RepoDetallesUsuario repoDetallesUsuario;

    public PerfilController(RepoDetallesUsuario repoDetallesUsuario) {
        this.repoDetallesUsuario = repoDetallesUsuario;
    }

    @PostMapping("/guardar")
    public String saveProfile(@ModelAttribute DetallesUsuario detallesUsuario, Model model) {
        repoDetallesUsuario.save(detallesUsuario);
        model.addText("saved");
        return "detallesUsuario";
    }
}
