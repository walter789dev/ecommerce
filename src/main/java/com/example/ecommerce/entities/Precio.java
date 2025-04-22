package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "precios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Precio extends Base {
    private double precio_costo;
    private double precio_venta;
}
