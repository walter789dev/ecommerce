package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.services.DireccionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direcciones")
public class DireccionController extends BaseController<Direccion, Long>{
    public DireccionController(DireccionService direccionService){
        super(direccionService);
    }
}
