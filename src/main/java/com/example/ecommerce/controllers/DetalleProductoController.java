package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.services.DetalleProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles_productos")
public class DetalleProductoController extends BaseController<DetalleProducto, Long>{

    @Autowired
    private DetalleProductoService detalleProductoService;

    public DetalleProductoController(DetalleProductoService detalleService){
        super(detalleService);
    }

    // Ver todos los productos del sexo pertinente.
    @GetMapping("/catalogo/{sexoStr}")
    public ResponseEntity<List<DetalleProducto>> getAllDetallesBySexo(
            @PathVariable String sexoStr
    ) {
        try {
            Sexo sexo = Sexo.valueOf(sexoStr.toUpperCase());

            List<DetalleProducto> detalles = detalleProductoService.getAllDetallesBySexo(sexo);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Ver los productos del sexo pertinente de acuerdo al catálogo seleccionado.
    @GetMapping("/catalogo/{sexoStr}")
    public ResponseEntity<List<DetalleProducto>> getAllDetallesBySexoAndTipoProducto(
            @RequestParam("sexo") String sexoStr, @RequestParam("tipoProducto") String tipoProductoStr
    ) {
        try {
            Sexo sexo = Sexo.valueOf(sexoStr.toUpperCase());
            TipoProducto tipoProducto = TipoProducto.valueOf(tipoProductoStr.toUpperCase());

            List<DetalleProducto> detalles = detalleProductoService.getAllDetallesBySexoAndTipoProducto(sexo, tipoProducto);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
