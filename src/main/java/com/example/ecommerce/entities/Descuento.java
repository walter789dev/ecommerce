package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "descuentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Descuento extends Base {
   private String nombre;
   private LocalDate fechaInicio;
   private LocalDate fechaFin;
   private double porcentaje;
}
