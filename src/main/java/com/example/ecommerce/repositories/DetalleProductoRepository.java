package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleProductoRepository extends BaseRepository<DetalleProducto, Long>{
    List<DetalleProducto> findAllByColor(String color);

    List<DetalleProducto> findAllByProductoId(Long idProducto);

    List<DetalleProducto> findAllByDescuentoId(Long idDescuento);

    @Query("SELECT dp FROM DetalleProducto dp JOIN dp.producto p WHERE p.sexo = :sexo AND p.tipoProducto = :tipoProducto")
    List<DetalleProducto> findAllByProductoSexoAndProductoTipoProducto(
            @Param("sexo") Sexo sexo,
            @Param("tipoProducto") TipoProducto tipoProducto
    );

    @Query("SELECT dp FROM DetalleProducto dp JOIN dp.producto p WHERE p.sexo = :sexo")
    List<DetalleProducto> findAllByProductoSexo(
            @Param("sexo") Sexo sexo
    );
}
