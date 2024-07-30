package com.eoi.tiendaderopa.controladores;

import ch.qos.logback.core.model.Model;
import com.eoi.tiendaderopa.entidades.GrupoTrabajo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class AppCallApiSenc4farming extends AbstractController <GrupoTrabajo> {
   //1:28 29/07/2024

    @Autowired
    private CsvGeneratorUtil csvGeneratorUtil;

    private final DatosLucas2018Service datosLucas2018Service;

    //...

@PostMapping("/api/credentials")
    public String getcredentials(@ModelAttribute(name = "consulta") Copernicuscredentials copernicuscredentials,
                                 HttpSession session, Model interfazConPantalla){
    String sessionKeycliid = "clienteid";
    String sessionKeySecret = "secret";
    String sessionAccess_token= "access_token";

    String defclientid = "sh-ef626da0-f7d8-40aa-a75d-9f84cac9871d";
    String defsecret = "2TyNYdw6t6U0gJjHl1v3kjQoKdy9mHVW";
    System.out.println("En Postmapping /api/credentials: tenemos datos en sesión:" + copernicuscredentials.getClientid());
    //session.setAttribute(sessionKeycliid,defclientid );
    //session.setAttribute(sessionKeySecret,defsecret);
    session.setAttribute(sessionKeycliid,copernicuscredentials.getClientid() );
    session.setAttribute(sessionKeySecret,copernicuscredentials.getSecret());
    //llamamos a pedir el token al api

}





}
