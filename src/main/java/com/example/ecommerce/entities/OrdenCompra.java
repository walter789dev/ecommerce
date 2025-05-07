package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ordenes_compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdenCompra extends Base {
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Date fecha_compra;
    private double total;
}
