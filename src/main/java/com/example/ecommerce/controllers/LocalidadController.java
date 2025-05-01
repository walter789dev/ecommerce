package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/localidades")
public class LocalidadController extends BaseController<Localidad, Long>{

    @Autowired
    private LocalidadService localidadService;

    public LocalidadController(LocalidadService localidadService){
        super(localidadService);
    }

    @GetMapping("/provincia/{idProvincia")
    public ResponseEntity<List<Localidad>> findAllByProvincia(@PathVariable Long idProvincia){
        try {
            List<Localidad> localidades = localidadService.findAllByProvincia(idProvincia);
            return ResponseEntity.ok(localidades);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }
    }
}
