package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.*;
import com.eoi.tiendaderopa.repositorios.*;
import com.eoi.tiendaderopa.servicios.SrvcPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/pago")
public class PagoCtrl {

    @Autowired
    private SrvcPago pagoSrvc;

    @Autowired
    private RepoProductoCarrito repoProductoCarrito;

    @Autowired
    private RepoCarrito repoCarrito;

    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private RepoVenta repoVenta;

    @PostMapping("/enviarinfo")
    public String mostrarFormularioEnvio(Model model , @RequestParam("idcarrito") String idcarrito) {
        model.addAttribute("idcarrito", idcarrito);
       // ArrayList<ProductoCarrito> productos = new ArrayList<>();
        // productos = (ArrayList<ProductoCarrito>) model.getAttribute("productos");
        return "enviarinfo";
    }

    @PostMapping("/datosPago")
    public String mostrarFormularioPago(
            @RequestParam("idcarrito") Long idcarrito,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("tel") String tel,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails)
    {

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("tel", tel);
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        model.addAttribute("address", address);
        List<ProductoCarrito> productos = new ArrayList<>();
        Carrito carrito = repoCarrito.findById(Long.valueOf(idcarrito)).get();
        productos = carrito.getListaProductosCarrito();
        Usuario usuario = repoUsuario.findByEmail(userDetails.getUsername());
        Pedido pedido = new Pedido();
        pedido.setCodigo_cliente(usuario.getId());
        pedido.setFecha_pedido(LocalDateTime.now());
        pedido.setFecha_entrega(LocalDateTime.now().plusDays(10L));
        pedido.setEstado("new");
        pedido.setUsuarioPedido(usuario);
        //TODO Aquí hay que rellenar todos los datos del pedido.
        pedido = repoPedido.save(pedido);
        final Pedido finalPedido = pedido;
        // Vamos a recorrer el Array de productos del carrito
        productos.forEach(productoCarrito ->  {
            Venta venta = new Venta();
            venta.setCantidad(productoCarrito.getQuantity());
            venta.setProducto(productoCarrito.getProducto());
            venta.setPrecio_unidad(productoCarrito.getProducto().getPrecio());
            venta.setPedido(finalPedido);
            repoVenta.save(venta);
        });
        return "pago";
    }

    @PostMapping("/procesarPago")
    public String procesarPago(
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("cardHolder") String cardHolder,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cvv") String cvv,
            Model model) {

        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cardHolder", cardHolder);
        model.addAttribute("expiryDate", expiryDate);
        model.addAttribute("cvv", cvv);

        Pago pago = new Pago();

        return "pagoexito";
    }



    // Este parámetro sirve para mostrar una lista de pagos
    @GetMapping("/lista")
    public String listarPagos(Model model) {

        List<Pago> pago = pagoSrvc.buscarEntidades();
        model.addAttribute("pagos", pago);
        model.addAttribute("titulo", "Pagos");
        return "pagos";
    }

    // Este parámetro sirve para crear un nuevo pago
    @PostMapping("/nuevo")
    public String crearPago(@RequestBody Pago pago, Model model) {
        try {
            pagoSrvc.guardar(pago);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/pagos";
    }

    // Este parámetro sirve para eliminar un método de pago
    @DeleteMapping("/pagos/{id}")
    public String eliminarPago(@PathVariable int id, Model model) {
        pagoSrvc.eliminarPorId(id);
        return "redirect:/pagos";
    }

}
