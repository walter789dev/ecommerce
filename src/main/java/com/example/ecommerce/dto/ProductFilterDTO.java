package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDTO {
    private String descuento;
    private List<RangoPrecio> rangosPrecios;

    private String talle;
    private String categoria;

    @Data
    public static class RangoPrecio {
        private Integer minPrecio;
        private Integer maxPrecio;
    }

}
