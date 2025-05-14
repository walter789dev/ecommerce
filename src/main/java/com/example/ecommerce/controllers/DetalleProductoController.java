package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.services.DetalleProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalles_productos")
public class DetalleProductoController extends BaseController<DetalleProducto, Long> {

    @Autowired
    private DetalleProductoService detalleProductoService;

    public DetalleProductoController(DetalleProductoService detalleService) {
        super(detalleService);
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<Stock>> getAllStockByDetalleProducto(@PathVariable Long id) {
        List<Stock> stocks = detalleProductoService.getAllStocksByDetalleProducto(id);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/producto/{idProducto}/colores")
    public ResponseEntity<List<String>> getAllColoresByProducto(
            @PathVariable Long idProducto) {
        List<String> colores = detalleProductoService.getAllColoresByProductoId(idProducto);
        return ResponseEntity.ok(colores);
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
        filtro.setSexo(sexo != null ? Sexo.valueOf(sexo.toUpperCase()): null);
        filtro.setTipoProducto(tipoProducto != null ? TipoProducto.valueOf(tipoProducto.toUpperCase()): null);
        filtro.setPrecioMin(precioMin);
        filtro.setPrecioMax(precioMax);
        filtro.setIdTalle(talle);
        filtro.setIdCategoria(categoria);

        List<DetalleProducto> resultados = detalleProductoService.getAllProductsFilter(filtro);
        return ResponseEntity.ok(resultados);
    }
}
