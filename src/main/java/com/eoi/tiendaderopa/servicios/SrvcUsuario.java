package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Rol;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoRol;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class SrvcUsuario extends AbstractBusinessSrvc<Usuario, Integer, RepoUsuario> {


    private final PasswordEncoder passwordEncoder;

    private RepoUsuario repoUsuario;

    private SrvcRol srvcRol;

    public SrvcUsuario(RepoUsuario repoUsuario, PasswordEncoder passwordEncoder,
                       SrvcRol srvcRol) {
        super(repoUsuario);
        this.passwordEncoder = passwordEncoder;
        this.srvcRol = srvcRol;
        this.repoUsuario = repoUsuario;
    }

    private static final Logger logger = LoggerFactory.getLogger(SrvcUsuario.class);


    public void registrarUsuario(Usuario usuario) {
        try {
            String encodedPassword = passwordEncoder.encode(usuario.getContraseña());
            logger.info("Codificando la contraseña para el usuario: " + usuario.getEmail());
            usuario.setContraseña(encodedPassword);
            logger.info("Contraseña codificada para {}: {}", usuario.getEmail(), encodedPassword);
            repoUsuario.save(usuario);

            Set<Rol> roles = new HashSet<>();
            roles.add(srvcRol.getRol("ROLE_USER"));
            usuario.setRoles(roles);

            logger.info("Usuario registrado con éxito: " + usuario.getEmail());

        } catch (Exception e) {
            logger.error("Error registrando el usuario: " + usuario.getEmail(), e);
            throw e;
        }

    }
}
