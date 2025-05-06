package com.example.ecommerce.services;

import com.example.ecommerce.entities.Imagen;
import com.example.ecommerce.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenService extends BaseService<Imagen, Long>{

    @Autowired
    private ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository){
        super(imagenRepository);
    }

    public List<Imagen> findAllByDetalleProducto(Long idDetalleProducto) throws Exception {
        try {
            return imagenRepository.findAllByDetalleProductoId(idDetalleProducto);
        }catch (Exception e){
            throw new Exception("No se han encontrado imagenes del producto", e);
        }
    }
}
