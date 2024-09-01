package com.eoi.tiendaderopa.controladores.admin;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/admin/productos")
public class ADMINProductoCtrl {

    private final SrvcProducto productoService;

    private final RepoProducto repoProducto;

    private static String UPLOADED_FOLDER = "imagenes/";

    public ADMINProductoCtrl(SrvcProducto productoService, RepoProducto repoProducto) {
        this.productoService = productoService; this.repoProducto = repoProducto;
    }


    @GetMapping("")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.buscarEntidades();
        model.addAttribute("productos", productos);
        return "admin/productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "admin/nuevoProducto";
    }

    @PostMapping("/nuevo")
    public String agregarProducto(@ModelAttribute Producto producto, @RequestParam("foto") MultipartFile foto) throws IOException {
        if (!foto.isEmpty()) {
                File directorio = new File(UPLOADED_FOLDER);
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

            byte[] bytes = foto.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            producto.setFotoUrl("/imagenes/" + foto.getOriginalFilename());
        }

        repoProducto.save(producto);
        return "redirect:/admin/productos";
    }



    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable("id") long id, Model model) {
        Optional<Producto> producto = repoProducto.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "admin/editarProducto";
        } else {
            return "redirect:/admin/productos";
        }
    }
    @PostMapping("/editar")
    public String actualizarProducto(@ModelAttribute Producto producto, @RequestParam("foto") MultipartFile foto) throws IOException {

        File directorio = new File(UPLOADED_FOLDER);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        Optional<Producto> productoExistenteOpt = repoProducto.findById(producto.getId());
        Producto productoExistente = productoExistenteOpt.get();

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setProveedor(producto.getProveedor());
        productoExistente.setMaterial(producto.getMaterial());
        productoExistente.setColor(producto.getColor());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setCantidadStock(producto.getCantidadStock());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setTalla(producto.getTalla());

        if (!foto.isEmpty()) {
            String filename = UUID.randomUUID() + "-" + foto.getOriginalFilename();
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            Files.write(path, foto.getBytes());
            System.out.println("Archivo guardado en: " + path);
            productoExistente.setFotoUrl("/imagenes/" + filename);
        }else {
            System.out.println("No se ha subido ninguna foto.");
        }


        repoProducto.save(productoExistente);
        return "redirect:/admin/productos";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") long id) {
        repoProducto.deleteById(id);
        return "redirect:/admin/productos";
    }

    @GetMapping("/{id}")
    public String verProducto(Model model, @PathVariable long id) {
        Optional<Producto> producto = productoService.encuentraPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
        }
        return "admin/detallesproducto";
    }
}






