package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.repositorios.RepoProducto;
import com.eoi.tiendaderopa.repositorios.RepoUsuario;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final RepoProducto repoProducto;
    private final SrvcProducto srvcProducto;
    private final RepoUsuario repoUsuario;

    public MainController(RepoProducto repoProducto, SrvcProducto srvcProducto, RepoUsuario repoUsuario) {
        this.repoProducto = repoProducto;
        this.srvcProducto = srvcProducto;
        this.repoUsuario = repoUsuario;
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


//    @GetMapping("/carrito")
//    public String carrito(Model model, HttpSession session) {
//        //Obtengo de la sesión el carrito
//        //List<Venta> list = model.getAttribute();
//        //model.getAttribute("carrito");
//        return "carrito";
//    }

    //1:48 29/07/2024
    //@GetMapping("/addcarrito")
    //public String addcarrito(Model model, HttpSession session) {
        //Obtengo de la sesión el carrito
        //List<Venta> list = (List<Venta>) model.getAttribute("carrito");
        //Integer num_elemen = list.size();
        //model.addAttribute("num_elem", num_elemen);
        //model.addAttribute("carrito", list);
        //2:03 29/7/2024
        //Random rand = new Random();
        //int rand_int = rand.nextInt(1000);
        //Añadir uno
        //Venta venta = new Venta();
        //venta.setId(rand_int);
        //venta.setProducto();
        //venta.setPrecio_unidad();
        //venta.setCantidad();
        //list.add(venta);
        //Elementos después
        //Integer num_elemento_despues = num_elemen - list.size();
        //model.addAttribute("num_elem", num_elemento_despues);
        //Devuelvo el dato de la sesión
        //model.getAttribute("carrito",list);
        //return "carrito2";
    //}

    //@GetMapping("/contarcarrito")
   // public String contarcarrito(Model model, HttpSession session) {
        //Obtengo de la sesión el carrito
       //List<Venta> list = (List<Venta>) model.getAttribute("carrito");
        //Integer num_elemen = list.size();
        //model.addAttribute("num_elem", num_elemen);
        //model.addAttribute("carrito", list);

        //return "carrito3";
    //}

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
