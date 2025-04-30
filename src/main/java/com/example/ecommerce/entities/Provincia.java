package com.example.ecommerce.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Enabled
@Table(name = "provincias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia extends Base{
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;
}
