package com.eoi.tiendaderopa.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "productodeseados")
@Getter
@Setter
public class ProductoDeseado {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Temporal(TemporalType.DATE)
        private Date date;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "product_id",nullable=false, updatable=false)
        private Producto producto;

        private String tokenSession;


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
            ProductoDeseado other = (ProductoDeseado) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
}
