package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.*;
import com.eoi.tiendaderopa.repositorios.*;
import com.eoi.tiendaderopa.servicios.SrvcPago;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/ventas")
public class PagoCtrl {

    private final SrvcPago pagoSrvc;

    private final RepoMetodoPago repoMetodoPago;

    private final RepoProductoCarrito repoProductoCarrito;

    private final RepoCarrito repoCarrito;

    private final RepoUsuario repoUsuario;

    private final RepoPedido repoPedido;

    private final RepoVenta repoVenta;

    public PagoCtrl(SrvcPago pagoSrvc, RepoMetodoPago repoMetodoPago, RepoProductoCarrito repoProductoCarrito, RepoCarrito repoCarrito, RepoUsuario repoUsuario, RepoPedido repoPedido, RepoVenta repoVenta) {
        this.pagoSrvc = pagoSrvc;
        this.repoMetodoPago = repoMetodoPago;
        this.repoProductoCarrito = repoProductoCarrito;
        this.repoCarrito = repoCarrito;
        this.repoUsuario = repoUsuario;
        this.repoPedido = repoPedido;
        this.repoVenta = repoVenta;
    }

    @PostMapping("/iniciarVenta")
    public String mostrarFormularioEnvio(Model model , @RequestParam("idcarrito") String idcarrito) {
        model.addAttribute("idcarrito", idcarrito);
        return "datosFacturacion";
    }


    @PostMapping("/datosFacturacion")
    public String ºmostrarFormularioPago(
            @RequestParam("idcarrito") long idcarrito,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("tel") String tel,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session)
    {
        String sessionToken = (String) session.getAttribute("sessionToken");

        if (sessionToken == null) {
            sessionToken = UUID.randomUUID().toString();
            session.setAttribute("sessionToken", sessionToken);
        }


        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("tel", tel);
        model.addAttribute("country", country);
        model.addAttribute("city", city);
        model.addAttribute("address", address);
        List<ProductoCarrito> productos = new ArrayList<>();
        Carrito carrito = repoCarrito.findById(idcarrito).get();
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
        session.setAttribute("pedidoId", pedido.getId());
        final Pedido finalPedido = pedido;
        // Vamos a recorrer el Array de productos del carrito
        productos.forEach(productoCarrito ->  {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setCantidad(productoCarrito.getQuantity());
            itemPedido.setProducto(productoCarrito.getProducto());
            itemPedido.setPrecio_unidad(productoCarrito.getProducto().getPrecio());
            itemPedido.setPedido(finalPedido);
            repoVenta.save(itemPedido);
        });
        return "redirect:/ventas/elegirMetodoPago";
    }


    @GetMapping("/elegirMetodoPago")
    public String elegirMetodoPago(Model model){
        List<MetodoPago> metodosPago = new ArrayList<>();
        //TODO - Hacer que filtre esta búsqueda por el usuario logeado (con el principal)
        metodosPago = repoMetodoPago.findAll();
        model.addAttribute("metodosPago",metodosPago);
        return "elegirMetodoPago";
    }


    @PostMapping("/crearMetodoPago")
    public String crearMetodoPago(
            @RequestParam("cardNumber") String cardNumber,
            @RequestParam("cardHolder") String cardHolder,
            @RequestParam("expiryDate") String expiryDate,
            @RequestParam("cvv") String cvv,
            Model model,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpSession session) {


        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("cardHolder", cardHolder);
        model.addAttribute("expiryDate", expiryDate);
        model.addAttribute("cvv", cvv);

        MetodoPago metodoPago = new MetodoPago(cardHolder, cardNumber, expiryDate, cvv);
        Usuario usuario = repoUsuario.findByEmail(userDetails.getUsername());
        metodoPago.setUsuario(usuario);
        repoMetodoPago.save(metodoPago);
        List<MetodoPago> metodosPago = new ArrayList<>();
        //TODO - Hacer que filtre esta búsqueda por el usuario logeado (con el principal)
        metodosPago = repoMetodoPago.findAll();
        model.addAttribute("metodosPago",metodosPago);

        return "elegirMetodoPago";
    }


    @PostMapping("/revisarDatos")
    public String revisarDatos(Model model, HttpSession session,
    @RequestParam("idMetodoPago") long idMetodoPago)
    {

        Factura factura = new Factura();
        int pedidoId = (int) session.getAttribute("pedidoId");
        Optional<Pedido> pedido = repoPedido.findById(Integer.valueOf(pedidoId));
        if (pedido.isPresent()) {
            model.addAttribute("pedido", pedido.get());
        }

        return "revisarDatos";
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
