package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categoria extends Base {
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private List<Producto> productos;
}
