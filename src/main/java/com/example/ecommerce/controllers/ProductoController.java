package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.services.ProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController extends BaseController<Producto, Long>{
    public ProductoController(ProductoService productoService){
        super(productoService);
    }
}
