package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoDetallesUsuario;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    private final RepoProducto repoProducto;
    private final RepoUsuario repoUsuario;
    private final RepoDetallesUsuario repoDetallesUsuario;

    public MainController(RepoProducto repoProducto, RepoUsuario repoUsuario, RepoDetallesUsuario repoDetallesUsuario) {
        this.repoProducto = repoProducto;
        this.repoUsuario = repoUsuario;
        this.repoDetallesUsuario = repoDetallesUsuario;
    }


    @GetMapping("/")
    public String indexPage(Model model, HttpSession session) {
        model.addAttribute("titulo", "Página Principal");
        if (session.getAttribute("sessionToken") == null) {
            session.setAttribute("sessionToken", UUID.randomUUID().toString());
        }
        List<Producto> productos = repoProducto.findAll();
        //añadido limite de 15 para limitar los que salen al principio y evitar que quede algun elemento suelto
        if (productos.size() > 15) {
            productos = productos.subList(0, 15);
        }
        List<List<Producto>> particiones = partitionList(productos, 3);
        model.addAttribute("particiones", particiones);
        model.addAttribute("productos", productos);

        return "index";
    }

    private <T> List<List<T>> partitionList(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/tienda")
    public String tienda(Model model) {
        return "tienda";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        return "contacto";
    }

    @GetMapping("/perfil")
    public String mostrarDetallesUsuario(@ModelAttribute DetallesUsuario detalle,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         Model model
    ) {
        Usuario usuario = repoUsuario.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);

        if(usuario.getDetalle() == null)
        {
            model.addAttribute("detalles", new DetallesUsuario());
        }
        else {
            model.addAttribute("detalles", usuario.getDetalle());
        }

        return "detallesUsuario";
    }

    @PostMapping("/perfil")
    public String grabarDetallesUsuario(@ModelAttribute DetallesUsuario detalle,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         Model model
    ) {
        Usuario usuario = repoUsuario.findByEmail(userDetails.getUsername());
        model.addAttribute("usuario", usuario);
        if(usuario.getDetalle() == null)
        {
            model.addAttribute("detalles", new DetallesUsuario());
        }
        else {
            model.addAttribute("detalles", usuario.getDetalle());
        }

        return "detallesUsuario";
    }


}
