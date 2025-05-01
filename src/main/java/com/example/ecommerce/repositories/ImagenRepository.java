package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Imagen;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends BaseRepository<Imagen, Long>{
    List<Imagen> findAllByDetalleProductoId(Long idDetalleProducto);
}
