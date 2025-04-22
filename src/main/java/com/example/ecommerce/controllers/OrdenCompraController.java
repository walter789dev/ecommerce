package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.OrdenCompra;
import com.example.ecommerce.services.OrdenCompraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordenes_compra")
public class OrdenCompraController extends BaseController<OrdenCompra, Long>{
    public OrdenCompraController(OrdenCompraService ordenCompraService){
        super(ordenCompraService);
    }
}
