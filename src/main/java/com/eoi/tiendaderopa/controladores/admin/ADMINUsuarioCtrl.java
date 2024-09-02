package com.eoi.tiendaderopa.controladores.admin;
import com.eoi.tiendaderopa.entidades.DetallesUsuario;
import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoFactura;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcDetallesUsuario;
import com.eoi.tiendaderopa.servicios.SrvcPedido;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/usuarios")
public class ADMINUsuarioCtrl {

    private final SrvcUsuario usuarioSrvc;
    private final RepoPedido repoPedido;
    private final RepoUsuario repoUsuario;
    private final SrvcPedido srvcPedido;
    private final SrvcDetallesUsuario srvcDetallesUsuario;

    @Autowired
    public ADMINUsuarioCtrl(SrvcUsuario usuarioSrvc,RepoUsuario repoUsuario, RepoPedido repoPedido, SrvcPedido srvcPedido, SrvcDetallesUsuario srvcDetallesUsuario) {
        this.usuarioSrvc = usuarioSrvc;
        this.repoPedido = repoPedido;
        this.repoUsuario = repoUsuario;
        this.srvcPedido = srvcPedido;
        this.srvcDetallesUsuario = srvcDetallesUsuario;

    }

    @GetMapping("")
    public String mostrarListaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioSrvc.buscarEntidades());
        return "admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id) {
        repoUsuario.deleteById(id);
        return "redirect:/admin/usuarios";
    }


    @GetMapping("/pedidos/{id}")
    public String mostrarPedidos(@PathVariable("id") int id, Model model) {
        List<Pedido> pedidos = srvcPedido.findPedidosByUsuarioId(id);
        if (pedidos.isEmpty()) {
            model.addAttribute("mensaje", "No hay pedidos para este usuario.");
        } else {
            model.addAttribute("pedidos", pedidos);
        }
        return "admin/pedidos";
    }

    @GetMapping("/detalles/{idUsuario}")
    public String mostrarDetallesUsuarioadmin(@PathVariable int idUsuario, Model model) {
        DetallesUsuario detalles = srvcDetallesUsuario.findDetallesByUsuarioId(idUsuario);
        if (detalles == null) {
            return "redirect:/admin/usuarios";
        }
        model.addAttribute("detalles", detalles);
        return "admin/detallesUsuario";
    }


}
