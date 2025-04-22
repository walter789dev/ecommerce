package com.example.ecommerce.services;

import com.example.ecommerce.entities.DetalleOrdenCompra;
import com.example.ecommerce.repositories.DetalleOrdenCompraRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenCompraService extends BaseService<DetalleOrdenCompra, Long>{
    public DetalleOrdenCompraService(DetalleOrdenCompraRepository detalleOrdenCompraRepository){
        super(detalleOrdenCompraRepository);
    }
}
