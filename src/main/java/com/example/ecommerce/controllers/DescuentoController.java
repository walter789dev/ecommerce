package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Descuento;
import com.example.ecommerce.services.DescuentoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descuentos")
public class DescuentoController extends BaseController<Descuento, Long>{
    public DescuentoController(DescuentoService descuentoService){
        super(descuentoService);
    }
}
