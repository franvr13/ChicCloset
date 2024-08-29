package com.eoi.tiendaderopa.controladores;

import com.eoi.tiendaderopa.entidades.Producto;
import com.eoi.tiendaderopa.servicios.SrvcBusqueda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BusquedaCtrl {

    @Autowired
    SrvcBusqueda busquedaSrvc;

    @PersistenceContext
    private EntityManager entityManager;

    
    @GetMapping("/busqueda")
    public String mostrarBusqueda(@RequestParam String busqueda, Model model) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> productoRoot = cq.from(Producto.class);

        // Separamos los terminos de la busqueda
        String[] terminos = normalize(busqueda).toLowerCase().split("\\s+");

        List<Predicate> predicates = new ArrayList<>();
        for (String termino : terminos) {
            String normalizedTerm = "%" + termino + "%";
            String oppositeGenderTerm = "%" + changeGender(termino) + "%";

            predicates.add(cb.or(
                    cb.like(cb.lower(productoRoot.get("material")), normalizedTerm),
                    cb.like(cb.lower(productoRoot.get("color")), normalizedTerm),
                    cb.like(cb.lower(productoRoot.get("color")), oppositeGenderTerm),
                    cb.like(cb.lower(productoRoot.get("descripcion")), normalizedTerm)
            ));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        List<Producto> productos = entityManager.createQuery(cq).getResultList();
        model.addAttribute("productos", productos);
        model.addAttribute("busqueda", busqueda);

        return "resultadosBusqueda";
    }

    // eliminar acentos y poner en minuscula
    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    // cambiar el genero de los colores
    private String changeGender(String term) {
        if (term.endsWith("o")) {
            return term.substring(0, term.length() - 1) + "a";
        } else if (term.endsWith("a")) {
            return term.substring(0, term.length() - 1) + "o";
        } else {
            return term;
        }
    }
}

