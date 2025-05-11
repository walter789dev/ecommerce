package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.services.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/direcciones")
public class DireccionController extends BaseController<Direccion, Long>{

    @Autowired
    private DireccionService direccionService;

    public DireccionController(DireccionService direccionService){
        super(direccionService);
    }

    @GetMapping("/localidad/{idLocalidad}")
    public ResponseEntity<List<Direccion>> findAllByLocalidad(@PathVariable Long idLocalidad){
        try {
            List<Direccion> direcciones = direccionService.findAllByLocalidad(idLocalidad);
            return ResponseEntity.ok(direcciones);
        }catch (Exception e){
            return  ResponseEntity.status(404).body(null);
        }
    }
}
