/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Product;
import com.example.field_market.entidades.Sale;
import com.example.field_market.entidades.Sales_Detaill;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.ProductRepository;
import com.example.field_market.repositorios.SaleRepository;
import com.example.field_market.request.SdetaillRequest;
import com.example.field_market.servicios.SdetailService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Cheryf
 */
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SaleDetailController {
    
    private final SdetailService sdetailService;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    
    //crear detalle de venta
    @PostMapping("/createSdetail")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createSdetail(@RequestBody SdetaillRequest request){
      try{
          String id_sale = request.getId_sale();
          Sale sale = saleRepository.findById(id_sale).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Venta no encontrada"));
          String id_product = request.getId_product();
          Product product = productRepository.findById(id_product).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"));
          
          Sales_Detaill sales_Detaill = new Sales_Detaill();
          sales_Detaill.setUnit_value(request.getUnit_value());
          sales_Detaill.setQuantity(request.getQuantity());
          sales_Detaill.setSale(sale);
          sales_Detaill.setProduct(product);
          
          sdetailService.createSdetaill(sales_Detaill);
          return new ResponseEntity<>("Detalle de venta creado",HttpStatus.CREATED);
      } catch (ResponseStatusException ex){
          return new ResponseEntity<>("Error al crear detalle de venta" + ex.getMessage(), ex.getStatus());
      } catch (Exception ex){
          return new ResponseEntity<>("Error al crear detalle de venta: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
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
    @PutMapping("/UpdateSdetail/{id_detail}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateSdetail(@PathVariable String id_detail, @RequestBody Sales_Detaill sales_Detaill) {
        try{
            sdetailService.updateSdetail(id_detail, sales_Detaill);
            return ResponseEntity.ok("Detalle de venta actualizado");
        } catch (MiException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar detalle de venta" + ex.getMessage());
        }
    }
    
    //eliminar Detalle de venta
     //@DeleteMapping("/{id_detail}")
    @PostMapping("/deleteSdetail/{id_detail}")//se cambio del de arriba por el de abajo para no tener conflictos con productoo 
    @CrossOrigin(origins = "http://localhost:3000")
     public ResponseEntity<String> deleteSale(@PathVariable("id_detail") String id_detail) {
        String result = sdetailService.deleteSdetail(id_detail);
        if (result.startsWith("El detalle con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
