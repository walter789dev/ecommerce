package com.example.ecommerce.services;

import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario, Long> {

   @Autowired
   private UsuarioRepository usuarioRepository;

   public UsuarioService(UsuarioRepository usuarioRepository) {
      super(usuarioRepository);
   }

   public Usuario findByUsername(String username) {
      return usuarioRepository.findByUsername(username).orElse(null);
   }

   @Override
   @Transactional
   public void delete(Long id) throws Exception {
      try {
         Optional<Usuario> optional = usuarioRepository.findById(id);
         if (optional.isPresent()) {
            Usuario user = optional.get();
            user.setActivo(!user.isActivo());
            usuarioRepository.save(user);
         } else {
            throw new Exception("Usuario no encontrado");
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }
}
