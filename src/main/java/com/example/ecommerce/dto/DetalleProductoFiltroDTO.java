package com.example.ecommerce.dto;

import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleProductoFiltroDTO {
    private String color;
    private Long idProducto;
    private Long idDescuento;
    private Sexo sexo;
    private TipoProducto tipoProducto;
    private Double precioMin;
    private Double precioMax;
    private Long idTalle;
    private Long idCategoria;
}
