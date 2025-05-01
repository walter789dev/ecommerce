package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.OrdenCompra;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends BaseRepository<OrdenCompra, Long>{
    List<OrdenCompra> findAllByUsuarioId(Long id);
}
