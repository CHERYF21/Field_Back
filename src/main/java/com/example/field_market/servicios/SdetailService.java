/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Sales_Detaill;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.SdetailRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cheryf
 */
@Service
@RequiredArgsConstructor
public class SdetailService {
    
    private final SdetailRepository sdetailRepository;
    
    //guardar detalle venta
    public Sales_Detaill createSdetaill(Sales_Detaill sales_Detaill){
       return sdetailRepository.save(sales_Detaill);
    }
    
    //listar detalles
    public List<Sales_Detaill> getAllSales_Detaill(){
        return sdetailRepository.findAll();
    }
    
    //detalle por su id
     public Optional<Sales_Detaill> getSdetailById(String id_detail) {
        return sdetailRepository.findById(id_detail);
    }
    
    //actualizar un detalle
    public void updateSdetail(String id_detail, Sales_Detaill updateSdetaill) throws MiException{
        Optional<Sales_Detaill> optionalSdetail = sdetailRepository.findById(id_detail);
        
        if(optionalSdetail.isPresent()){
           Sales_Detaill sales_Detaill = optionalSdetail.get();
           
           sales_Detaill.setUnit_value(updateSdetaill.getUnit_value());
           sales_Detaill.setQuantity(updateSdetaill.getQuantity());
           //establlecer la venta
           sales_Detaill.setSale(updateSdetaill.getSale());
           //establecer el producto
           sales_Detaill.setProduct(updateSdetaill.getProduct());
           sdetailRepository.save(sales_Detaill);
        } else {
           throw new MiException("Detalle de venta no encontrado");
        }  
    }
    
    //eliminar Detalle de venta
    public String deleteSdetail(String id_detail){
        Optional<Sales_Detaill> optionalSdetail = sdetailRepository.findById(id_detail);
        
        if(optionalSdetail.isPresent()){
           sdetailRepository.deleteById(id_detail);
           return "Detalle de venta eliminado con exito";
        }else{
            return "La venta con el ID: "+ id_detail + "no existe";
        }
    }
    
    
}
