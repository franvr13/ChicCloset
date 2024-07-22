package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.api.APIUsuariosCtrl;
import com.eoi.tiendaderopa.dto.*;
import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.security.SecurityConfig;
import com.eoi.tiendaderopa.servicios.SrvcCarrito;
import com.eoi.tiendaderopa.servicios.SrvcProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.jaasapi.JaasApiIntegrationFilter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CarritoCtrl {
    @Autowired
    private SrvcCarrito srvcCarrito;

    @Autowired
    private SrvcProducto srvcProducto;

    @Autowired
    private SecurityConfig securityConfig;

    @PostMapping("/add")
    public ResponseEntity<APIUsuariosCtrl> addToCart(@RequestBody AddToCarritoDTO addToCarritoDTO, @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        securityConfig.authenticate(token);

        int userId = securityConfig.getUser(token).getId();

        SrvcCarrito.AddToCarritoDTO(addToCarritoDTO,userId);

        return new ResponseEntity<APIUsuariosCtrl>(new APIUsuariosCtrl(true, "Added to cart"), HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<CosteCarrito> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        securityConfig.authenticate(token);
        int userId = securityConfig.getUser(token).getId();
        CosteCarrito costeCarrito = srvcCarrito.listCartItems(userId);
        return new ResponseEntity<CosteCarrito>(costeCarrito,HttpStatus.OK);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<APIUsuariosCtrl> updateCartItem(@RequestBody @Validated AddToCarritoDTO cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();

        Producto producto = srvcProducto.getProductById(cartDto.getProductoId());

        srvcCarrito.updateCartItem(cartDto,userId,producto);
        return new ResponseEntity<APIUsuariosCtrl>(new JaasApiIntegrationFilter(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<APIUsuariosCtrl> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        securityConfig.authenticate(token);

        int userId = securityConfig.getUser(token).getId();

        srvcCarrito.deleteCartItem(itemID,userId);
        return new ResponseEntity<APIUsuariosCtrl>(new APIUsuariosCtrl(true, "Item has been removed"), HttpStatus.OK);
    }

}