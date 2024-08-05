package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity(name = "deseados")
public class Deseados {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Temporal(TemporalType.DATE)
        private Date date;
        private String sessionToken;
        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
        private Set<ProductoDeseados> producto = new HashSet<ProductoDeseados>();

        public Deseados() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }

        public Set<ProductoDeseados> getProducto() {
            return producto;
        }

        public void setProducto(Set<ProductoDeseados> producto) {
            this.producto = producto;
        }
}
