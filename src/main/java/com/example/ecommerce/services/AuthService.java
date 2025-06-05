package com.example.ecommerce.services;

import com.example.ecommerce.auth.AuthResponse;
import com.example.ecommerce.auth.LoginRequest;
import com.example.ecommerce.auth.RegisterRequest;
import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.entities.enums.Rol;
import com.example.ecommerce.jwt.JwtService;
import com.example.ecommerce.repositories.DireccionRepository;
import com.example.ecommerce.repositories.LocalidadRepository;
import com.example.ecommerce.repositories.ProvinciaRepository;
import com.example.ecommerce.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
   private final UsuarioRepository usuarioRepository;
   private final LocalidadRepository localidadRepository;
   private final DireccionRepository direccionRepository;
   private final ProvinciaRepository provinciaRepository;
   private final JwtService jwtService;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;

   public AuthResponse login(LoginRequest request) {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
      UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow(
            () -> new UsernameNotFoundException("Usuario no encontrado - Modificado"));
      String token = jwtService.getToken(user);
      return AuthResponse.builder()
            .token(token)
            .build();
   }

   public AuthResponse register(RegisterRequest request) {
      Provincia provincia = provinciaRepository.findByNombre(request.getProvincia().toLowerCase());

      if (provincia == null) {
         provincia = Provincia.builder()
               .nombre(request.getProvincia())
               .build();
         provinciaRepository.save(provincia);
      }

      Localidad localidad = localidadRepository.findByNombre(request.getLocalidad().toLowerCase());

      if (localidad == null) {
         localidad = Localidad.builder()
               .nombre(request.getLocalidad())
               .codigoPostal(request.getCodigoPostal())
               .provincia(provincia)
               .build();
         localidadRepository.save(localidad);
      }

      Direccion direccion = Direccion.builder()
            .domicilio(request.getDireccion())
            .localidad(localidad)
            .build();

      direccionRepository.save(direccion);

      Usuario usuario = Usuario.builder()
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .dni(request.getDni())
            .username(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .rol(Rol.valueOf(request.getRol()))
            .direccion(direccion)
            .build();

      usuarioRepository.save(usuario);
      return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
   }
}
