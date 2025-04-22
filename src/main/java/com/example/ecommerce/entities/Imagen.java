package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "imagenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagen extends Base{
    private String url;
    private String alt;

    @ManyToOne
    @JoinColumn(name = "id_detalleProducto")
    private DetalleProducto detalleProducto;
}
