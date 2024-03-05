/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Sale;
import com.example.field_market.servicios.SaleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cheryf
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class SaleController {
    
    private final SaleService saleService;
    
    //Crear Sale
    @PostMapping("/createSale")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        Sale createdSale = saleService.createSale(sale);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    //obtener todas las ventas
    @GetMapping("/listSale")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    // para obtener una venta por su ID
    @GetMapping("/{id_sale}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id_sale") String id_sale) {
        Optional<Sale> sale = saleService.getSaleById(id_sale);
        return sale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // para actualizar una venta
    @PutMapping("/{id_sale}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateSale(@PathVariable("id_sale") String id_sale, @RequestBody Sale updatedSale) {
        String result = saleService.updateSale(id_sale, updatedSale);
        if (result.startsWith("La venta con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //para eliminar una venta
    @DeleteMapping("/{id_sale}")
    public ResponseEntity<String> deleteSale(@PathVariable("id_sale") String id_sale) {
        String result = saleService.deleteSale(id_sale);
        if (result.startsWith("La venta con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}
