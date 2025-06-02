package com.example.ecommerce.entities;

import com.example.ecommerce.entities.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends Base implements UserDetails {
   private String nombre;
   private String apellido;
   private String password;
   private int dni;

   @Column(nullable = false)
   private String username;

   @Enumerated(EnumType.STRING)
   private Rol rol;

   @ManyToOne
   @JoinColumn(name = "id_direccion")
   private Direccion direccion;

   @Builder.Default
   private boolean activo = true;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority(rol.name()));
   }
}
