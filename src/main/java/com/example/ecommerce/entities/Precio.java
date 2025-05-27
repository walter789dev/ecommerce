package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "precios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Precio extends Base {
   private double precio_compra;
   private double precio_venta;
}
