package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.CarritoDTO;
import com.example.ecommerce.dto.OrderDetailDTO;
import com.example.ecommerce.entities.DetalleOrdenCompra;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.services.OrdenCompraService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class MercadoPagoController {

   private final OrdenCompraService ordenCompraService;

   @Value("${mercadopago.access-token}")
   private String mercadoPagoAccessToken;

   @PostMapping("/mp")
   public ResponseEntity<String> mp(@RequestBody CarritoDTO carrito) throws Exception {
      try {
         MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

         List<PreferenceItemRequest> items = new ArrayList<>();

         OrderDetailDTO detallesOrden = ordenCompraService.crearOrdenCompra(carrito);

         List<DetalleOrdenCompra> detallesOrdenCompras = detallesOrden.getDetallesOrdenCompras();

         for (DetalleOrdenCompra detalle : detallesOrdenCompras) {
            DetalleProducto detalleProducto = detalle.getDetalleProducto();
            double precioFinal = detalleProducto.isActivo()
                  ? detalleProducto.getPrecioVenta() - detalleProducto.getDescuento().getPorcentaje()
                  : detalleProducto.getPrecioVenta();

            PreferenceItemRequest item = PreferenceItemRequest.builder()
                  .id(detalle.getId().toString())
                  .title(detalleProducto.getProducto().getNombre())
                  .quantity(detalle.getCantidad())
                  .currencyId("ARS")
                  .unitPrice(BigDecimal.valueOf(precioFinal))
                  .build();

            items.add(item);
         }

         PreferenceBackUrlsRequest backUrls =
               PreferenceBackUrlsRequest.builder()
                     .success("https://localhost:5173/pagoExitoso")
                     .pending("https://localhost:5173/")
                     .failure("https://localhost:5173/pagoFracaso")
                     .build();

         List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
         excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

         PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
               .excludedPaymentTypes(excludedPaymentTypes)
               .installments(1)
               .build();

         PreferenceRequest preferenceRequest = PreferenceRequest.builder()
               .items(items)
               .backUrls(backUrls)
               .paymentMethods(paymentMethods)
               .autoReturn("approved")
               .build();

         PreferenceClient client = new PreferenceClient();
         Preference preference = client.create(preferenceRequest);
         String prefId = preference.getId();

         return ResponseEntity.status(HttpStatus.OK).body("{\"preferenceId\":\"" + prefId + "\"}");
      } catch (MPApiException e) {
         var apiResponse = e.getApiResponse();
         String content = apiResponse.getContent();
         int statusCode = apiResponse.getStatusCode();
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(content + " " + statusCode);
      }
   }
}
