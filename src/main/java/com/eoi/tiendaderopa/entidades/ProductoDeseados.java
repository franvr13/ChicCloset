package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "productodeseados")
public class ProductoDeseados {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Temporal(TemporalType.DATE)
        private Date date;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product_id",nullable=false, updatable=false)
        private Producto producto;
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
        public Producto getProducto() {
            return producto;
        }
        public void setProducto(Producto producto) {
            this.producto = producto;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ProductoDeseados other = (ProductoDeseados) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
}
