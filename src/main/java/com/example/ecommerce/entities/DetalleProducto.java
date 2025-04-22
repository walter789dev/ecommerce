package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "detalles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProducto extends Base{
    private int stock;
    private String color;
    private boolean estado;
}
