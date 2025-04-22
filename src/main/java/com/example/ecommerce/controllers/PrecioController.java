package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Precio;
import com.example.ecommerce.services.PrecioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/precios")
public class PrecioController extends BaseController<Precio, Long>{
    public PrecioController(PrecioService precioService){
        super(precioService);
    }
}
