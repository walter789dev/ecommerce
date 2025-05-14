package com.example.ecommerce.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock extends Base{
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_talle")
    private Talle talle;
}
