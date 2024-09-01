package com.eoi.tiendaderopa.controladores.admin;
import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Usuario;
import com.eoi.tiendaderopa.repositorios.RepoFactura;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcPedido;
import com.eoi.tiendaderopa.servicios.SrvcUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin/usuarios")
public class ADMINUsuarioCtrl {

    private final SrvcUsuario usuarioSrvc;
    private final RepoPedido repoPedido;
    private final RepoUsuario repoUsuario;
    private final SrvcPedido srvcPedido;

    @Autowired
    public ADMINUsuarioCtrl(SrvcUsuario usuarioSrvc,RepoUsuario repoUsuario, RepoPedido repoPedido, SrvcPedido srvcPedido) {
        this.usuarioSrvc = usuarioSrvc;
        this.repoPedido = repoPedido;
        this.repoUsuario = repoUsuario;
        this.srvcPedido = srvcPedido;

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


}
