package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.PrecioProducto;
import com.example.ecommerce.services.PrecioProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/precios")
public class PrecioProductoController extends BaseController<PrecioProducto, Long>{
    public PrecioProductoController(PrecioProductoService precioService){
        super(precioService);
    }
}
