/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Product;
import com.example.field_market.request.ProductRequest;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cheryf
 */
@RestController
@RequestMapping("/auth")
public class MercadoPago {
    
     @PostMapping("/create")
public ResponseEntity<String> payment(@RequestBody List<ProductRequest> productos) throws MPException {
    MercadoPagoConfig.setAccessToken("TEST-1384405973768600-031922-9823f9e8a8c4a79820d7e154faad58a2-1733948455");

    List<PreferenceItemRequest> items = new ArrayList<>();

    for (ProductRequest producto : productos) {
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
            .id(producto.getId_product())
            .title(producto.getTitle())
            .description(producto.getDescripcion())
            .categoryId(producto.getCategory())
            .quantity(producto.getQuantity())
            .currencyId("COP")
            .unitPrice(new BigDecimal(producto.getPrice()))
            .build();

        items.add(itemRequest);
    }

    PreferenceRequest preferenceRequest = PreferenceRequest.builder()
            .items(items)
            .build();

    try {
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);
        ///
        System.out.println(preference.getInitPoint());
        return ResponseEntity.ok(preference.getInitPoint());
    } catch (MPApiException ex) {
        System.out.printf(ex.getMessage());
        return ResponseEntity.ok("payment no");
    } catch (MPException ex) {
        ex.printStackTrace();
        return ResponseEntity.ok("payment no 2");
    }
}

}
