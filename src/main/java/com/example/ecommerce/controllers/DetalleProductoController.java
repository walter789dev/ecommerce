package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.services.DetalleProductoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/detalles_productos")
public class DetalleProductoController extends BaseController<DetalleProducto, Long>{
    public DetalleProductoController(DetalleProductoService detalleService){
        super(detalleService);
    }
}
