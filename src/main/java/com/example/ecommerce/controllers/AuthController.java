package com.example.ecommerce.controllers;

import com.example.ecommerce.auth.AuthResponse;
import com.example.ecommerce.auth.LoginRequest;
import com.example.ecommerce.auth.RegisterRequest;
import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.services.AuthService;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;
   private final UsuarioService usuarioService;

   @PostMapping("/login")
   public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
      return ResponseEntity.ok(authService.login(request));
   }

   @PostMapping("/register")
   public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
      return ResponseEntity.ok(authService.register(request));
   }

   @GetMapping("/profile")
   public ResponseEntity<Usuario> findByUsername(Principal principal) {
      String username = principal.getName();
      Usuario usuario = usuarioService.findByUsername(username);
      return ResponseEntity.ok(usuario);
   }
}
