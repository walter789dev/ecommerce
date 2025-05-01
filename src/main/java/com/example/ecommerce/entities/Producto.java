package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto extends Base {
    private String nombre;
    private String sexo;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @OneToOne
    @JoinColumn(name = "id_precio")
    private PrecioProducto precio;

    @ManyToMany
    @JoinTable(
            name = "productos_categorias",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();
}
