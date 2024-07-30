package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.entidades.Venta;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final RepoProducto repoProducto;

    public MainController(RepoProducto repoProducto) {
        this.repoProducto = repoProducto;
    }


    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("titulo", "Página Principal");
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


    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session) {
        //Obtengo de la sesión el carrito
        List<Venta> list = session.setAttribute();
        session.setAttribute("carrito");
        return "carrito";
    }

    //1:48 29/07/2024
    @GetMapping("/addcarrito")
    public String carrito(Model model, HttpSession session) {
        //Obtengo de la sesión el carrito
        List<Venta> list = (List<Venta>) session.getAttribute("carrito");
        Integer num_elemen = list.size();
        interfazConPantalla.addAttribute("num_elem", num_elemen);
        interfazConPantalla.addAttribute("carrito", list);
        //Añadir uno
        Venta venta = new Venta();
        venta.setId();
        venta.setProducto();
        venta.setPrecio_unidad();
        venta.setCantidad();
        list.add(venta);
        //Elementos después
        Integer num_elemento_depsues = num_elemen - list.size();
        interfazConPantalla.addAttribute("num_elem", num_elemento_depsues);
        //Devuelvo el dato de la sesión
        session.getAttribute("carrito",list);
        return "carrito2";
    }

    @GetMapping("/contarcarrito")
    public String carrito(Model model, HttpSession session) {
        //Obtengo de la sesión el carrito
        List<Venta> list = (List<Venta>) session.getAttribute("carrito");
        Integer num_elemen = list.size();
        interfazConPantalla.addAttribute("num_elem", num_elemen);
        interfazConPantalla.addAttribute("carrito", list);

        return "carrito3";
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


}
