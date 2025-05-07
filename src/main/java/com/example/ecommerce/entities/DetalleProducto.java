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

    @ManyToOne
    @JoinColumn(name = "id_descuento")
    private Descuento descuento;

    @OneToMany
    @JoinColumn(name = "id_imagen")
    private List<Imagen> imagenes;
}
