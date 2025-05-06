package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Imagen;
import com.example.ecommerce.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenController extends BaseController<Imagen, Long>{

    @Autowired
    private ImagenService imagenService;

    public ImagenController(ImagenService imagenService){
        super(imagenService);
    }

    @GetMapping("/detalleProducto/{idDetalle}")
    public ResponseEntity<List<Imagen>> findAllByDetalleProducto(@PathVariable Long idDetalle){
        try {
            List<Imagen> imagenes = imagenService.findAllByDetalleProducto(idDetalle);
            return ResponseEntity.ok(imagenes);
        }catch (Exception e){
            return ResponseEntity.status(404).body(null);
        }
    }

}
