package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion extends Base {
    private String pais;
    private String provincia;
    private String departamento;
    private String localidad;
}
