package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends BaseRepository<Stock, Long> {
}
