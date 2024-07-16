package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Usuario;

// Encriptación de las contraseñas de los usuarios
public interface IUsuarioSrvc {
    public String getEncodedPassword (Usuario usuario);
}
