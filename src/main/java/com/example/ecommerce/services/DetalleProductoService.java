package com.example.ecommerce.services;

import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleProductoService extends BaseService<DetalleProducto, Long>{
    public DetalleProductoService(DetalleProductoRepository detalleRepository){
        super(detalleRepository);
    }
}
