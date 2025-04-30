package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.services.LocalidadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "localidades")
public class LocalidadController extends BaseController<Localidad, Long>{
    public LocalidadController(LocalidadService localidadService){
        super(localidadService);
    }
}
