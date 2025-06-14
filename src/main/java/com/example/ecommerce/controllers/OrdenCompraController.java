package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.OrdenCompra;
import com.example.ecommerce.services.OrdenCompraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ordenes_compras")
public class OrdenCompraController extends BaseController<OrdenCompra, Long>{
    public OrdenCompraController(OrdenCompraService ordenCompraService){
        super(ordenCompraService);
    }
}
