package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.servicios.SrvcBusqueda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class BusquedaCtrl {

    @Autowired
    SrvcBusqueda busquedaSrvc;

    @PersistenceContext
    private EntityManager entityManager;

    //modificar
    @GetMapping("/busqueda")
    public String mostrarBusqueda(@RequestParam String busqueda, Model model) {


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> productoRoot = cq.from(Producto.class);
        Set<Predicate> predicates = new HashSet<>();

        //Esto es para que cada palabra se recoja como un termino diferente
        String[] terminos = busqueda.split("\\s+");

        for (String termino : terminos) {
            termino = termino.toLowerCase();
            predicates.add(cb.or(cb.like(productoRoot.get("material"), "%" + termino + "%"),
                    cb.like(productoRoot.get("color"), "%" + termino + "%"),
                    cb.like(productoRoot.get("descripcion"), "%" + termino + "%")));


        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));


        return entityManager.createQuery(cq).getResultList().toString();
    }
}
