package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_talle")
    private Talle talle;

    @OneToOne
    @JoinColumn(name = "id_precio")
    private PrecioProducto precio;

    @OneToMany
    @JoinColumn(name = "id_imagen")
    private List<Imagen> imagenes;
}
