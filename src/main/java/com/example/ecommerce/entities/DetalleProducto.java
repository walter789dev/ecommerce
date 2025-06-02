package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detalles_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleProducto extends Base {
   private String color;
   @Builder.Default
   private boolean activo = true;

   @ManyToOne
   @JoinColumn(name = "id_producto")
   private Producto producto;

   private double precioCompra;
   private double precioVenta;

   @ManyToOne
   @JoinColumn(name = "id_descuento")
   private Descuento descuento;

   @OneToMany
   @JoinColumn(name = "id_detalle_producto")
   private List<Imagen> imagenes;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "id_detalle_producto") // FK en Stock
   private List<Stock> stocks;

}
