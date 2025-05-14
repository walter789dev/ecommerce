package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController extends BaseController<Stock, Long>{

    @Autowired
    private StockService stockService;

    public StockController(StockService stockService){
        super(stockService);
    }
}
