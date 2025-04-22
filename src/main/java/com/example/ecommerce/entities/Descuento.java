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
@Table(name = "descuentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Descuento extends Base {
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidad;
}
