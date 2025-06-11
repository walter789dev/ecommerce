package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.OrdenCompra;
import com.example.ecommerce.entities.enums.Estado;
import com.example.ecommerce.repositories.OrdenCompraRepository;
import com.example.ecommerce.services.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordenes_compras")
public class OrdenCompraController extends BaseController<OrdenCompra, Long> {

   @Autowired
   private OrdenCompraService ordenCompraService;
   @Autowired
   private OrdenCompraRepository ordenCompraRepository;

   public OrdenCompraController(OrdenCompraService ordenCompraService) {
      super(ordenCompraService);
   }

   @PutMapping("/usuario/{id}")
   public boolean getOrdenCompra(@PathVariable Long id) throws Exception {
      try {
         OrdenCompra ordenCompra = ordenCompraService.findByUsuarioId(id);
         ordenCompra.setEstado(Estado.APROBADO);
         ordenCompraRepository.save(ordenCompra);
         return true;
      } catch (Exception e) {
         return ResponseEntity.notFound().build().hasBody();
      }
   }
}
