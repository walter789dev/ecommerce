package com.example.ecommerce.services;

import com.example.ecommerce.dto.CarritoDTO;
import com.example.ecommerce.dto.CarritoItemDTO;
import com.example.ecommerce.entities.DetalleOrdenCompra;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.OrdenCompra;
import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.repositories.OrdenCompraRepository;
import com.example.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra, Long> {

   @Autowired
   private DetalleProductoRepository detalleProductoRepository;
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private OrdenCompraRepository ordenCompraRepository;

   public OrdenCompraService(OrdenCompraRepository ordenCompraRepository) {
      super(ordenCompraRepository);
   }

   public List<DetalleOrdenCompra> crearOrdenCompra(CarritoDTO carrito) throws Exception {
      List<DetalleOrdenCompra> productos = new ArrayList<>();
      double precioTotal = 0.0;

      for (CarritoItemDTO item : carrito.getProductos()) {
         DetalleProducto detalleProducto = detalleProductoRepository.findById(item.getId()).get();
         precioTotal += detalleProducto.getPrecioVenta() * item.getCantidad();

         DetalleOrdenCompra detalleOrdenCompra = DetalleOrdenCompra.builder()
               .cantidad(item.getCantidad())
               .detalleProducto(detalleProducto)
               .build();
         productos.add(detalleOrdenCompra);
      }

      Usuario usuario = usuarioRepository.findById(carrito.getIdUsuario()).get();

      OrdenCompra ordenCompra = OrdenCompra.builder()
            .total(precioTotal)
            .fechaCompra(LocalDate.from(LocalDateTime.now()))
            .detalleOrdenCompras(productos)
            .total(precioTotal)
            .usuario(usuario)
            .build();

      ordenCompraRepository.save(ordenCompra);
      return productos;
   }
}
