package com.example.ecommerce.services;

import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService extends BaseService<Stock, Long>{

    @Autowired
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        super(stockRepository);
    }
}
