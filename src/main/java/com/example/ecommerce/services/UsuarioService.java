package com.example.ecommerce.services;

import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Long>{
    public UsuarioService(UsuarioRepository usuarioRepository){
        super(usuarioRepository);
    }
}
