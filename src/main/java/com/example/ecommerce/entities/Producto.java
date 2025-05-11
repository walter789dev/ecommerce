package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto extends Base {
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private double precio_compra;
    private double precio_venta;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;
}
