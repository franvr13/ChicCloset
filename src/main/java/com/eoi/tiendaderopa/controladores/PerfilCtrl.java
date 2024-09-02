package com.eoi.tiendaderopa.controladores;

import ch.qos.logback.core.model.Model;
import com.eoi.tiendaderopa.entidades.Perfil;
import com.eoi.tiendaderopa.repositorios.RepoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class PerfilCtrl {

    @Autowired
    private RepoPerfil repoPerfil;

    @PostMapping("/usuarios")
    public String saveProfile(@ModelAttribute Perfil perfil, Model model) {
        repoPerfil.save(perfil);
        model.addText("saved");
        return "usuarios";
    }
}
