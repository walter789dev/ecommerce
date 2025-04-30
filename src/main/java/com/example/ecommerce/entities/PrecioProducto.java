package com.example.ecommerce.entities;

import jakarta.persistence.*;
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
public class PrecioProducto extends Base {
    private double precio_compra;
    private double precio_venta;

    @ManyToOne
    @JoinColumn(name = "id_descuento")
    private Descuento descuento;
}
