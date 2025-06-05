package com.example.ecommerce.dto;

import com.example.ecommerce.entities.DetalleOrdenCompra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
   private List<DetalleOrdenCompra> detallesOrdenCompras;
   private List<Double> preciosDescuentos;
}
