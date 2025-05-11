package com.example.ecommerce.services;

import com.example.ecommerce.dto.ProductFilterDTO;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.entities.enums.Sexo;
import com.example.ecommerce.entities.enums.TipoProducto;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.specification.ProductSpecification;
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

    public List<DetalleProducto> getAllDetallesBySexoAndTipoProducto(Sexo sexo, TipoProducto tipoProducto) {
        return detalleProductoRepository.findAllByProductoSexoAndProductoTipoProducto(sexo, tipoProducto);
    }

    public List<DetalleProducto> getAllDetallesBySexo(Sexo sexo) {
        return detalleProductoRepository.findAllByProductoSexo(sexo);
    }
}
