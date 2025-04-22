package com.example.ecommerce.services;

import com.example.ecommerce.entities.Imagen;
import com.example.ecommerce.repositories.ImagenRepository;
import org.springframework.stereotype.Service;

@Service
public class ImagenService extends BaseService<Imagen, Long>{
    public ImagenService(ImagenRepository imagenRepository){
        super(imagenRepository);
    }
}
