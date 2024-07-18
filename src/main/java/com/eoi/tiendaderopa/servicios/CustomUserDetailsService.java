package com.eoi.tiendaderopa.servicios;

import com.eoi.tiendaderopa.entidades.Rol;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Servicio personalizado de autenticación de usuarios.
 * Implementa la interfaz {@link UserDetailsService} de Spring Security para proporcionar
 * una forma de recuperar los detalles del usuario desde la base de datos utilizando un nombre de usuario.
 *
 * <p>
 * Esta clase se marca con la anotación {@link Service} para indicar que es un componente de servicio
 * de Spring, y se puede inyectar en otros componentes mediante la inyección de dependencias.
 * </p>
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RepoUsuario usuarioRepo;
    private final MessageSource messageSource;
    private final RepoUsuario repoUsuario;

    /**
     * Constructor de la clase que recibe un {@link RepoUsuario} para interactuar con la base de datos
     * y un {@link MessageSource} para la internacionalización de mensajes.
     *
     * <p>
     * La inyección de dependencias es un patrón que permite que las dependencias de una clase sean
     * proporcionadas por el contenedor de Spring, en lugar de ser creadas manualmente dentro de la clase.
     * Esto facilita la gestión de las dependencias y mejora la capacidad de prueba y mantenimiento del código.
     * </p>
     *
     * @param usuarioRepo el repositorio de usuarios que se utilizará para recuperar los datos del usuario.
     * @param messageSource el componente de Spring utilizado para la internacionalización de mensajes.
     */

    public CustomUserDetailsService(RepoUsuario usuarioRepo, MessageSource messageSource, RepoUsuario repoUsuario) {
        this.usuarioRepo = usuarioRepo;
        this.messageSource = messageSource;
        this.repoUsuario = repoUsuario;
    }

    /**
     * Sobrescribe el método {@link UserDetailsService#loadUserByUsername(String)} para cargar
     * los detalles del usuario desde la base de datos utilizando el nombre de usuario.
     *
     * @param username el nombre de usuario utilizado para buscar al usuario.
     * @return un objeto {@link UserDetails} que contiene la información del usuario.
     * @throws UsernameNotFoundException si no se encuentra ningún usuario con el nombre de usuario proporcionado.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repoUsuario.findByEmail(username);
        if (usuario==null){
            throw new UsernameNotFoundException("Usuario no encontrado"+ username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Rol rol: usuario.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(rol.getRolNombre()));
        }
        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), grantedAuthorities);
    }
}
