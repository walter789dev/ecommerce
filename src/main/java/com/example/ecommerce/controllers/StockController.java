package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.services.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stocks_productos")
public class StockController extends BaseController<Stock, Long>{
    public StockController(StockService stockService){
        super(stockService);
    }
}
