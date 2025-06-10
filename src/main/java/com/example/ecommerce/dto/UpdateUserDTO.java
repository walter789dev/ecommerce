package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
   private Long id;
   private String nombre;
   private String apellido;
   private String username;
   private String password;
   private String domicilio;
   private String dni;
   private String localidad;
   private String provincia;
   private String rol;
   private boolean activo;
}
