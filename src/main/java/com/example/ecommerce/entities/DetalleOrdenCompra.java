package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Estado;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detalles_ordenes_compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleOrdenCompra extends Base {
   @ManyToOne
   @JoinColumn(name = "id_detalleProducto")
   private DetalleProducto detalleProducto;

   private int cantidad;
   private Talle talle;
}
