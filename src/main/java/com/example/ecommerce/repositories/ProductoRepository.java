package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, Long> {
}
