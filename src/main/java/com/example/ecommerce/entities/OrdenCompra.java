package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ordenes_compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenCompra extends Base {
   @ManyToOne
   @JoinColumn(name = "id_usuario")
   private Usuario usuario;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "orden_compra_id")
   private List<DetalleOrdenCompra> detalleOrdenCompras;

   private LocalDate fechaCompra;
   private double total;
}
