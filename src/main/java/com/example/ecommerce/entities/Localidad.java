package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "localidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Localidad extends Base{
    private String nombre;
    private int codigoPostal;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;
}
