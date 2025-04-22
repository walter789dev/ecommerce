package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios_direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDireccion extends Base{
    private String calle;
}
