package com.example.ecommerce.auth;

import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.entities.enums.Rol;
import com.example.ecommerce.jwt.JwtService;
import com.example.ecommerce.repositories.DireccionRepository;
import com.example.ecommerce.repositories.LocalidadRepository;
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
        Localidad localidad = localidadRepository.findByNombre("Rosario");

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
                .rol(Rol.USUARIO)
                .direccion(direccion)
                .build();

        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
