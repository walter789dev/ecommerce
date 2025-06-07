package com.example.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

   @Transient
   @JsonProperty("activo")
   public boolean isActivo() {
      LocalDate hoy = LocalDate.now();
      return (fechaInicio == null || !hoy.isBefore(fechaInicio)) &&
            (fechaFin == null || !hoy.isAfter(fechaFin));
   }
}
