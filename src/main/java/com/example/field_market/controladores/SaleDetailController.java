/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Sales_Detaill;
import com.example.field_market.servicios.SdetailService;
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
public class SaleDetailController {
    
    private final SdetailService sdetailService;
    
    //crear detalle de venta
    @PostMapping("/createSdetail")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Sales_Detaill> createSdetail(@RequestBody Sales_Detaill sales_Detaill ){
      Sales_Detaill createdSdetail = sdetailService.createSdetaill(sales_Detaill);
      return new ResponseEntity<>(createdSdetail, HttpStatus.CREATED);
    } 
    
    //obtener todos los detalles de venta
    @GetMapping("/listSdetail")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity <List<Sales_Detaill>> getAllSdetail(){
        List<Sales_Detaill> Sdetail = sdetailService.getAllSales_Detaill();
        return new ResponseEntity<>(Sdetail, HttpStatus.OK);
    }
    
    //obtener un detalle de venta en especifico
    @GetMapping("/{id_detail}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Sales_Detaill> getSdetailById(@PathVariable("id_detail") String id_detail){
        Optional<Sales_Detaill> Sdetail = sdetailService.getSdetailById(id_detail);
        return Sdetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    //actualizar un detalle de venta
    @PutMapping("/{id_detail}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateSdetail(@PathVariable("id_detail") String id_detail, @RequestBody Sales_Detaill updatedSdetail) {
        String result = sdetailService.updateSdetail(id_detail, updatedSdetail);
        if (result.startsWith("El detalle con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    //eliminar Detalle de venta
     @DeleteMapping("/{id_detail}")
     @CrossOrigin(origins = "http://localhost:3000")
     public ResponseEntity<String> deleteSale(@PathVariable("id_detail") String id_detail) {
        String result = sdetailService.deleteSdetail(id_detail);
        if (result.startsWith("El detalle con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
