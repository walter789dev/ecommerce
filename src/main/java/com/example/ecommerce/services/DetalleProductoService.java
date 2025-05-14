package com.example.ecommerce.services;

import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.entities.Talle;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.specification.DetalleProductoSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleProductoService extends BaseService<DetalleProducto, Long>{

    @Autowired
    private DetalleProductoRepository detalleProductoRepository;

    public DetalleProductoService(DetalleProductoRepository detalleProductoRepository){
        super(detalleProductoRepository);
    }

    public List<String> getAllColoresByProductoId(Long productoId) {
        return detalleProductoRepository.findColoresByProductoId(productoId);
    }

    public List<DetalleProducto> getAllProductsFilter(DetalleProductoFiltroDTO filtro) {
        return detalleProductoRepository.findAll(DetalleProductoSpecification.filterDetalles(filtro));
    }

    public List<Stock> getAllStocksByDetalleProducto(Long detalleProductoId) {
        DetalleProducto detalleProducto = detalleProductoRepository.findById(detalleProductoId)
                .orElseThrow(() -> new EntityNotFoundException("DetalleProducto no encontrado"));
        return detalleProducto.getStocks();
    }
}
