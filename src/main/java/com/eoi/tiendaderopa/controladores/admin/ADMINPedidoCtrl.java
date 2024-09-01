package com.eoi.tiendaderopa.controladores.admin;

import com.eoi.tiendaderopa.entidades.Pedido;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoPedido;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcPedido;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/admin/pedidos")
public class ADMINPedidoCtrl {

    private final SrvcPedido srvcPedido;

    private final RepoPedido repoPedido;

    public ADMINPedidoCtrl(SrvcPedido srvcPedido, RepoPedido repoPedido) {
        this.srvcPedido = srvcPedido;
        this.repoPedido = repoPedido;
    }


    @GetMapping("/detalles/{id}")
    public String mostrarDetallesPedido(@PathVariable("id") int id, Model model) {
        Optional<Pedido> pedido = repoPedido.findById(id);
        if (pedido.isPresent()) {
            model.addAttribute("pedido", pedido.get());
            return "admin/detallesPedido";
        } else {
            return "redirect:/admin/usuarios";
        }
    }

    @PostMapping("/cambiarestado/{id}")
    public String cambiarEstadoPedido(@PathVariable("id") int id, @RequestParam("nuevoEstado") String nuevoEstado) {
        Optional<Pedido> pedidoOpt = repoPedido.findById(id);

        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstado(nuevoEstado);
            repoPedido.save(pedido);
            return "redirect:/admin/pedidos/detalles/" + id;
        } else {
            return "redirect:/admin/usuarios";
        }
    }




}






