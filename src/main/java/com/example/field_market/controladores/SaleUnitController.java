/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Sales_unit;
import com.example.field_market.entidades.Usuario;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.UsuarioRepository;
import com.example.field_market.request.saleUnitRequest;
import com.example.field_market.servicios.UnitSaleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class SaleUnitController {
    
    private final UnitSaleService unitSaleService;
    
    private final UsuarioRepository usuarioRepository;
    
    //crear unidad de venta
    @PostMapping("/createsaleunit")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createSaleunit(@RequestBody saleUnitRequest request){
        try{
            String id = request.getId();
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"usuario no encontrado"));
            
            Sales_unit unit= new Sales_unit();
            unit.setUnidad(request.getUnidad());
            unit.setUsuario(usuario);
            
            unitSaleService.createUnitSale(unit);
            return new ResponseEntity<>("Unidad de venta creada",HttpStatus.CREATED);  
        }catch(ResponseStatusException ex){
            return new ResponseEntity<>("Error al crear unidad de venta" + ex.getMessage(),ex.getStatus());
        } catch (MiException ex){
            return new ResponseEntity<>("Error al crear unidad de venta: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //listar unidades de venta
    @GetMapping("/listunit")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Sales_unit>> getAllUnit(){
        List<Sales_unit> unit = unitSaleService.getAllSalesUnit();
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }
    
   @DeleteMapping("/deletaUnit/{id_saleUnit}")
   @CrossOrigin(origins = "http://localhost:3000")
   public ResponseEntity<String> deleteUnit(@PathVariable("id_saleUnit") String id_saleUnit){
       String result = unitSaleService.deleteSaleUnit(id_saleUnit);
       if(result.startsWith("La undad de medida")){
           return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(result, HttpStatus.OK);
   }
}
