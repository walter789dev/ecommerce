package com.example.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "talles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Talle extends Base{
    private String name;
}
