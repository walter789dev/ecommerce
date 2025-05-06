package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.DetalleProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleProductoRepository extends BaseRepository<DetalleProducto, Long>{
    List<DetalleProducto> findAllByColor(String color);

    List<DetalleProducto> findAllByProductoId(Long idProducto);

    List<DetalleProducto> findAllByTalleId(Long idTalle);

    List<DetalleProducto> findAllByDescuentoId(Long idDescuento);
}
