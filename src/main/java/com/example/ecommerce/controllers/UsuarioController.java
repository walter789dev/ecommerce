package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.UpdateUserDTO;
import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.entities.enums.Rol;
import com.example.ecommerce.repositories.DireccionRepository;
import com.example.ecommerce.repositories.LocalidadRepository;
import com.example.ecommerce.repositories.ProvinciaRepository;
import com.example.ecommerce.repositories.UsuarioRepository;
import com.example.ecommerce.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController extends BaseController<Usuario, Long> {

   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private UsuarioRepository usuarioRepository;
   @Autowired
   private LocalidadRepository localidadRepository;
   @Autowired
   private DireccionRepository direccionRepository;
   @Autowired
   private ProvinciaRepository provinciaRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;

   public UsuarioController(UsuarioService usuarioService) {
      super(usuarioService);
   }

   @GetMapping("/username/{username}")
   public ResponseEntity<Usuario> findByUsername(@PathVariable String username) {
      Usuario usuario = usuarioService.findByUsername(username);
      return ResponseEntity.ok(usuario);
   }

   @Override
   @PutMapping("/{id}")
   public ResponseEntity<Usuario> update(@PathVariable Long id, Usuario usuario) throws Exception {
      try {
         Provincia provincia = usuario.getDireccion().getLocalidad().getProvincia();
         Provincia newProvincia = provinciaRepository.findByNombre(provincia.getNombre().toLowerCase());

         if (newProvincia == null) {
            provincia = Provincia.builder()
                  .nombre(provincia.getNombre())
                  .build();
            provinciaRepository.save(provincia);
         }

         Localidad oldLocalidad = usuario.getDireccion().getLocalidad();
         Localidad localidad = localidadRepository.findByNombre(oldLocalidad.getNombre().toLowerCase());

         if (localidad == null) {
            localidad = Localidad.builder()
                  .nombre(oldLocalidad.getNombre())
                  .provincia(newProvincia)
                  .build();
            localidadRepository.save(localidad);
         }

         Direccion oldDireccion = usuario.getDireccion();
         Direccion direccion = direccionRepository.findByDomicilio(oldDireccion.getDomicilio());

         if (direccion == null) {
            direccion = Direccion.builder()
                  .domicilio(oldDireccion.getDomicilio())
                  .localidad(localidad)
                  .build();
            direccionRepository.save(direccion);
         }

         Usuario user = usuarioRepository.findById(id).get();
         user.setNombre(usuario.getNombre());
         user.setApellido(usuario.getApellido());
         user.setDni(usuario.getDni());
         user.setUsername(usuario.getUsername());
         user.setPassword(passwordEncoder.encode(usuario.getPassword()));
         user.setRol(usuario.getRol());
         user.setDireccion(direccion);


         return ResponseEntity.ok(usuarioRepository.save(user));
      } catch (UsernameNotFoundException e) {
         // Si el usuario no se encuentra (ej. si usas UserDetailsService para buscar por ID y no solo username)
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // O un mensaje de error detallado
      } catch (IllegalArgumentException e) {
         // Puedes lanzar esta si hay un problema con los argumentos del request
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // O un mensaje de error detallado
      } catch (Exception e) {
         System.err.println("Error inesperado en update de usuario: " + e.getMessage()); // Imprimir el error para depurar
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // O un mensaje de error más específico
      }
   }
}
