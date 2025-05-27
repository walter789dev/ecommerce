package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Precio;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioRepository extends BaseRepository<Precio, Long> {
}
