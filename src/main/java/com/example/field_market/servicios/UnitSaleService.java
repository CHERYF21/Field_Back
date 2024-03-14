/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Sales_unit;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.SalesUnitRepository;
import com.example.field_market.repositorios.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cheryf
 */
@Service
@RequiredArgsConstructor
public class UnitSaleService {
    
    private final SalesUnitRepository salesUnitRepository;
    
    @Autowired
     UsuarioRepository usuarioRepository;
    
    @Transactional
    public void createUnitSale(Sales_unit sales_unit) throws MiException{
        validateSalesUnit(sales_unit);
        
        salesUnitRepository.save(sales_unit);
    }
    
    public List<Sales_unit> getAllSalesUnit(){
        return salesUnitRepository.findAll();
    }
    
    public void updateSalesUnit(String id_saleUnit, Sales_unit Updatesales_unit ) throws MiException{
        Optional<Sales_unit> optionalSaleUnit = salesUnitRepository.findById(id_saleUnit);
        
        if(optionalSaleUnit.isPresent()){
            Sales_unit unit = optionalSaleUnit.get();
            
            unit.setUnidad(Updatesales_unit.getUnidad());
            unit.setUsuario(Updatesales_unit.getUsuario());
            salesUnitRepository.save(unit);
        }else{
            throw new MiException("la unidad de venta no puede ser null");
        }
    }
    
    public String deleteSaleUnit(String id_saleUnit){
        Optional<Sales_unit> optionalSalesUnit = salesUnitRepository.findById(id_saleUnit);
        
        if(optionalSalesUnit.isPresent()){
            salesUnitRepository.deleteById(id_saleUnit);
            return "Unidad de compra eliminada";
        } else {
            return "la unidad de venta ID: " + id_saleUnit + "no existe";
        }
    }
    
    private void validateSalesUnit(Sales_unit sales_unit) throws MiException{
        if( sales_unit == null ){
            throw new MiException ("el objeto unidad de venta no puede ser null");
        }
        if(sales_unit.getUnidad() == null){
            throw new MiException ("la unidad de venta no puede ser null");
        }
    } 
}
