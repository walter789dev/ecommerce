package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.DetalleOrdenCompra;
import com.example.ecommerce.services.DetalleOrdenCompraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/detalles_ordenes_compra")
public class DetalleOrdenCompraController extends BaseController<DetalleOrdenCompra, Long>{
    public DetalleOrdenCompraController(DetalleOrdenCompraService detalleOrdenCompraService){
        super(detalleOrdenCompraService);
    }
}
