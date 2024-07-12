package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.*;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class SrvcUsuario extends AbstractBusinessSrvc<Usuario, Integer, RepoUsuario> {
    @Autowired
    public SrvcUsuario(RepoUsuario usuarioRepo) {
        super(usuarioRepo);
    }


    //se podria hacer directamente con save de abstract service??
    public void login(String usuario, String password) {
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // contraseña????
        return getRepo().save(usuario);
    }

    //esta anotacion para asegurarnos que el proceso se realiza completo o no se realiza
    @Transactional
    public Usuario guardarDetallesUsuario(Integer idUsuario, DetallesUsuario detalles) {
        Usuario usuario = obtenerPorIdNoOp(idUsuario);
        usuario.setDetalle(detalles);
        getRepo().save(usuario);

        return usuario;
    }

    @Transactional
    public DatosFacturacion guardarDatosFacturacion(Integer idUsuario, DatosFacturacion datosFacturacion) {
        Usuario usuario = obtenerPorIdNoOp(idUsuario);
        usuario.setDatosFacturacion((Set<DatosFacturacion>) datosFacturacion);
        getRepo().save(usuario);

        return datosFacturacion;

    }



}
