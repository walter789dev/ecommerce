package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detalles_ordenes_compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenCompra extends Base {
    private int cantidad;
}
