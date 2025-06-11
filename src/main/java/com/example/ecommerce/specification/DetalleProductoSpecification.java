package com.example.ecommerce.specification;

import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.entities.Stock;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DetalleProductoSpecification {

   public static Specification<DetalleProducto> filterDetalles(DetalleProductoFiltroDTO filtro) {
      return (root, query, cb) -> {
         List<Predicate> predicates = new ArrayList<>();

         if (filtro.isActivo()) {
            predicates.add(cb.isTrue(root.get("activo")));
         }

         if (filtro.getColor() != null) {
            predicates.add(cb.equal(root.get("color"), filtro.getColor()));
         }

         if (filtro.getIdProducto() != null) {
            predicates.add(cb.equal(root.get("producto").get("id"), filtro.getIdProducto()));
         }

         if (filtro.getIdDescuento() != null) {
            predicates.add(cb.equal(root.get("descuento").get("id"), filtro.getIdDescuento()));
         }

         if (filtro.getSexo() != null) {
            predicates.add(cb.equal(root.get("producto").get("sexo"), filtro.getSexo()));
         }

         if (filtro.getTipoProducto() != null) {
            predicates.add(cb.equal(root.get("producto").get("tipoProducto"), filtro.getTipoProducto()));
         }

         if (filtro.getPrecioMin() != null && filtro.getPrecioMax() != null) {
            predicates.add(cb.between(
                  root.get("precioVenta"),
                  filtro.getPrecioMin(),
                  filtro.getPrecioMax()
            ));
         }

         if (filtro.getIdTalle() != null) {
            Join<DetalleProducto, Stock> stockJoin = root.join("stocks", JoinType.INNER);
            predicates.add(cb.equal(stockJoin.get("talle").get("id"), filtro.getIdTalle()));
         }

         if (filtro.getIdCategoria() != null) {
            Join<DetalleProducto, Producto> productoJoin = root.join("producto");
            Join<Producto, Categoria> categoriaJoin = productoJoin.join("categorias");
            predicates.add(cb.equal(categoriaJoin.get("id"), filtro.getIdCategoria()));
         }

         return cb.and(predicates.toArray(new Predicate[0]));
      };
   }
}
