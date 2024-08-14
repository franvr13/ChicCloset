package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SrvcUsuario extends AbstractBusinessSrvc<Usuario, Integer, RepoUsuario> {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    public SrvcUsuario(RepoUsuario repoUsuario, PasswordEncoder passwordEncoder) {
        super(repoUsuario);
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(SrvcUsuario.class);

    public void registrarUsuario(Usuario usuario) {
        try {
            String encodedPassword = passwordEncoder.encode(usuario.getContraseña());
            logger.info("Codificando la contraseña para el usuario: " + usuario.getEmail());
            usuario.setContraseña(encodedPassword);
            logger.info("Contraseña codificada para {}: {}", usuario.getEmail(), encodedPassword);
            repoUsuario.save(usuario);
            logger.info("Usuario registrado con éxito: " + usuario.getEmail());
        } catch (Exception e) {
            logger.error("Error registrando el usuario: " + usuario.getEmail(), e);
            throw e;
        }

    }
}
