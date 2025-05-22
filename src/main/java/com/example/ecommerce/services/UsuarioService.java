package com.example.ecommerce.services;

import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Long>{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        super(usuarioRepository);
    }

    public Usuario findByUsername(String username){
        return usuarioRepository.findByUsername(username).orElse(null);
    }
}
