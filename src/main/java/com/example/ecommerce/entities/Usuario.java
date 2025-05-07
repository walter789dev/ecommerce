package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private int dni;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
}
