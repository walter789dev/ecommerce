package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provincias")
public class ProvinciaController extends BaseController<Provincia, Long>{

    @Autowired
    private ProvinciaService provinciaService;

    public ProvinciaController(ProvinciaService provinciaService){
        super(provinciaService);
    }

    @GetMapping("/pais/{idPais}")
    public ResponseEntity<List<Provincia>> findAllByPais(@PathVariable Long idPais){
        try {
            List<Provincia> provincias = provinciaService.findAllByPais(idPais);
            return ResponseEntity.ok(provincias);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }
    }
}
