package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.DetalleProducto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleProductoRepository extends BaseRepository<DetalleProducto, Long>, JpaSpecificationExecutor<DetalleProducto> {
   @Query("SELECT DISTINCT dp.color FROM DetalleProducto dp WHERE dp.producto.id = :productoId AND dp.activo = true")
   List<String> findColoresByProductoId(@Param("productoId") Long productoId);

   @Query("SELECT * FROM DetalleProducto dp WHERE dp.activo = true")
   List<DetalleProducto> findAllActive();
}
