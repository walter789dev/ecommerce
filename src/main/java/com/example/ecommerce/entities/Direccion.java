package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Direccion extends Base {
    private String domicilio;
    private String casa;

    @ManyToOne
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
}
