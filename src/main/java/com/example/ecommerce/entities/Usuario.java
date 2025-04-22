package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Base {
    private String nombre;
    private String email;
    private String password;
    private int dni;
    private Rol rol;
}
