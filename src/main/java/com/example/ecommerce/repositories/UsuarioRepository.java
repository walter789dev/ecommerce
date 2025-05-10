package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
