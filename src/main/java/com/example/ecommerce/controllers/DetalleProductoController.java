package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.DetalleColoresDTO;
import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.repositories.ProductoRepository;
import com.example.ecommerce.services.DetalleProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles_productos")
public class DetalleProductoController extends BaseController<DetalleProducto, Long> {

   @Autowired
   private DetalleProductoService detalleProductoService;
   @Autowired
   private DetalleProductoRepository detalleProductoRepository;
   @Autowired
   private ProductoRepository productoRepository;

   public DetalleProductoController(DetalleProductoService detalleService) {
      super(detalleService);
   }

   @GetMapping("/colores/producto/{productoId}")
   public ResponseEntity<List<DetalleColoresDTO>> getAllColoresByProductoId(@PathVariable Long productoId) {
      try {
         List<DetalleColoresDTO> detalles = detalleProductoService.getAllColoresByProductoId(productoId);
         return ResponseEntity.ok(detalles);
      } catch (EntityNotFoundException e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/producto/{productoId}")
   public ResponseEntity<List<DetalleProducto>> getAllDetallesByProductoId(@PathVariable Long productoId) {
      try {
         List<DetalleProducto> detalles = detalleProductoService.getAllDetallesByProductoId(productoId);
         return ResponseEntity.ok(detalles);
      } catch (EntityNotFoundException e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/catalogo")
   public ResponseEntity<List<DetalleProducto>> getAllDetallesByFilter(
         @RequestParam(required = false) Long descuento,
         @RequestParam(required = false) String sexo,
         @RequestParam(required = false) String tipoProducto,
         @RequestParam(required = false) Double precioMin,
         @RequestParam(required = false) Double precioMax,
         @RequestParam(required = false) Long talle,
         @RequestParam(required = false) Long categoria
   ) {
      DetalleProductoFiltroDTO filtro = new DetalleProductoFiltroDTO();
      filtro.setIdDescuento(descuento);
      filtro.setSexo(sexo != null ? Sexo.valueOf(sexo.toUpperCase()) : null);
      filtro.setTipoProducto(tipoProducto != null ? TipoProducto.valueOf(tipoProducto.toUpperCase()) : null);
      filtro.setPrecioMin(precioMin);
      filtro.setPrecioMax(precioMax);
      filtro.setIdTalle(talle);
      filtro.setIdCategoria(categoria);

      List<DetalleProducto> resultados = detalleProductoService.getAllProductsFilter(filtro);
      return ResponseEntity.ok(resultados);
   }

   @DeleteMapping("/activate/{id}")
   public ResponseEntity<Producto> activateDetailsByProductId(@PathVariable Long id) {
      try {
         Producto producto = productoRepository.findById(id).get();
         boolean activate = producto.isActivo();

         producto.setActivo(!activate);
         productoRepository.save(producto);

         List<DetalleProducto> detalleProductos = detalleProductoService.getAllDetallesByProductoId(id);
         for (DetalleProducto detalleProducto : detalleProductos) {
            detalleProducto.setActivo(!activate);
            detalleProductoRepository.save(detalleProducto);
         }

         return ResponseEntity.ok(producto);
      } catch (EntityNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
}
