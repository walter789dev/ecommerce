package com.example.ecommerce.services;

import com.example.ecommerce.dto.CarritoDTO;
import com.example.ecommerce.dto.CarritoItemDTO;
import com.example.ecommerce.dto.OrderDetailDTO;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra, Long> {

   @Autowired
   private DetalleProductoRepository detalleProductoRepository;
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private OrdenCompraRepository ordenCompraRepository;
   @Autowired
   private StockRepository stockRepository;

   public OrdenCompraService(OrdenCompraRepository ordenCompraRepository) {
      super(ordenCompraRepository);
   }

   public OrdenCompra findByUsuarioId(Long id) {
      try {
         return ordenCompraRepository.findByUsuarioId(id);
      } catch (Exception e) {
         throw new RuntimeException("No se encontró la orden de compra");
      }
   }

   public OrderDetailDTO crearOrdenCompra(CarritoDTO carrito) throws Exception {
      List<DetalleOrdenCompra> productos = new ArrayList<>();

      for (CarritoItemDTO item : carrito.getProductos()) {
         DetalleProducto detalleProducto = detalleProductoRepository.findById(item.getId()).get();
         Talle talle = null;

         for (Stock stock : detalleProducto.getStocks()) {
            if (Objects.equals(stock.getTalle().getId(), item.getIdTalle())) {
               stock.setStock(stock.getStock() - item.getCantidad());

               talle = stock.getTalle();
               stockRepository.save(stock);
            }
         }

         DetalleOrdenCompra detalleOrdenCompra = DetalleOrdenCompra.builder()
               .cantidad(item.getCantidad())
               .talle(talle)
               .detalleProducto(detalleProducto)
               .build();

         productos.add(detalleOrdenCompra);
      }

      Usuario usuario = usuarioRepository.findById(carrito.getIdUsuario()).get();

      OrdenCompra ordenCompra = OrdenCompra.builder()
            .total(carrito.getTotal())
            .fechaCompra(LocalDate.from(LocalDateTime.now()))
            .detalleOrdenCompras(productos)
            .usuario(usuario)
            .build();

      ordenCompraRepository.save(ordenCompra);

      return OrderDetailDTO.builder()
            .detallesOrdenCompras(productos)
            .build();
   }
}
