package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ordenes_compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompra extends Base {
    private double total;
    private double descuento;
    private Date fecha_compra;
}
