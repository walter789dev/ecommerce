package com.example.ecommerce.services;

import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService extends BaseService<Stock, Long>{
    public StockService(StockRepository stockRepository){
        super(stockRepository);
    }
}
