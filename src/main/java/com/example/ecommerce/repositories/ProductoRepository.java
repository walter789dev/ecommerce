package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.entities.enums.TipoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long>{
    List<Producto> findAllBySexo(String sexo);

    @Query("SELECT p FROM Producto p WHERE p.precio_venta BETWEEN :minPrice AND :maxPrice")
    List<Producto> findAllByPrecioBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    List<Producto> findAllByTipoProducto(TipoProducto tipoProducto);
}
