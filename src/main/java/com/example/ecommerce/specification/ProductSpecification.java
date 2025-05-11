package com.example.ecommerce.specification;

import com.example.ecommerce.dto.ProductFilterDTO;
import com.example.ecommerce.entities.DetalleProducto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductSpecification implements Specification<DetalleProducto> {

    private ProductFilterDTO filtros;

    @Override
    public Predicate toPredicate(Root<DetalleProducto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filtros.getDescuento() != null && !filtros.getDescuento().isEmpty()) {
            predicates.add(root.get("descuento").in(filtros.getDescuento()));
        }

        if (filtros.getRangosPrecios() != null && !filtros.getRangosPrecios().isEmpty()) {
            List<Predicate> precioPredicates = new ArrayList<>();
            for (ProductFilterDTO.RangoPrecio rango : filtros.getRangosPrecios()) {
                if (rango.getMinPrecio() != null && rango.getMaxPrecio() != null) {
                    precioPredicates.add(criteriaBuilder.between(root.get("precio"), rango.getMinPrecio(), rango.getMaxPrecio()));
                } else if (rango.getMinPrecio() != null) {
                    precioPredicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), rango.getMinPrecio()));
                } else if (rango.getMaxPrecio() != null) {
                    precioPredicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precio"), rango.getMaxPrecio()));
                }
            }
            predicates.add(criteriaBuilder.or(precioPredicates.toArray(new Predicate[0])));
        }

        if (filtros.getTalle() != null && !filtros.getTalle().isEmpty()) {
            predicates.add(root.get("talle").in(filtros.getTalle()));
        }

        if (filtros.getCategoria() != null && !filtros.getCategoria().isEmpty()) {
            predicates.add(root.get("categoria").in(filtros.getCategoria()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
