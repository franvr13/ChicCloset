package com.eoi.tiendaderopa.config;

import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase que se ejecuta al iniciar la aplicación. Implementa {@link ApplicationListener}
 * para escuchar el evento {@link ApplicationReadyEvent}, indicando que la aplicación
 * está lista para recibir solicitudes.
 *
 * <p>
 * Al implementar {@link ApplicationListener}, esta clase puede reaccionar a eventos específicos
 * del ciclo de vida de la aplicación. En este caso, estamos escuchando el evento
 * {@link ApplicationReadyEvent}, que se dispara cuando la aplicación ha completado el
 * proceso de arranque y está lista para servir peticiones.
 * </p>
 *
 * <p>
 * Esta clase se utiliza para inicializar datos en la base de datos, como la creación de un usuario
 * predeterminado al inicio de la aplicación.
 * </p>
 *
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final RepoUsuario usuarioRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor de la clase que recibe un {@link RepoUsuario} para interactuar con la base de datos.
     *
     * @param usuarioRepo el repositorio de usuarios que se utilizará para guardar los datos del usuario.
     */

    public ApplicationStartup(RepoUsuario usuarioRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
    }

    /**
     * Este método se ejecuta tan pronto como sea posible para indicar que
     * la aplicación está lista para atender solicitudes.
     *
     * <p>
     * En este método se crea un usuario predeterminado y se guarda en la base de datos
     * utilizando el {@link RepoUsuario}. Este enfoque permite inicializar datos críticos
     * o de prueba en la base de datos automáticamente cuando la aplicación se inicia por primera vez.
     * </p>
     *
     * @param event el evento que indica que la aplicación está lista.
     */

//    @Override
//    public void onApplicationEvent(final ApplicationReadyEvent event) {
//        Usuario usuario = new Usuario(1L, "user", "pepe", "lopez", "mail@mail.com", bCryptPasswordEncoder.encode("password"), "admin");
//        usuarioRepo.save(usuario);
//        return;
//    }
}
