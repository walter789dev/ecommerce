package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto extends Base {
    private String nombre;
    private String sexo;
}
