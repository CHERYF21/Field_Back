/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Sale;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.SaleRepository;
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
public class SaleService {
    
    private final SaleRepository saleRepository;
    @Autowired
    UsuarioRepository usuarioRepository; 
    
    @Transactional
    public void createSale(Sale sale) throws MiException{
        validateSale(sale);
        
        saleRepository.save(sale);
    }

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Obtener una vunta por su ID
    public Optional<Sale> getSaleById(String id_sale) {
        return saleRepository.findById(id_sale);
    }

    // Actualizar una venta
    public void updateSale(String id_sale, Sale updatedSale) throws MiException {
    Optional<Sale> optionalSale = saleRepository.findById(id_sale);

    if (optionalSale.isPresent()) {
        Sale sale = optionalSale.get();
        
        sale.setDate_sale(updatedSale.getDate_sale());
        sale.setTotal_paid(updatedSale.getTotal_paid());
        //establecer el usuario de la venta
        sale.setUsuario(updatedSale.getUsuario());
        saleRepository.save(sale);
        
    } else {
        throw new MiException("Venta no encontrada");
    }
}

    // Eliminar una venta
    public String deleteSale(String id_sale) {
        Optional<Sale> optionalSale = saleRepository.findById(id_sale);

        if (optionalSale.isPresent()) {
            saleRepository.deleteById(id_sale);
            return "Venta eliminada exitosamente.";
        } else {
            return "La venta con el ID " + id_sale + " no existe.";
        }
    }
    
    private void validateSale(Sale sale) throws MiException{
        if (sale == null){
            throw new MiException("La venta no se encuentra");
        }
        if(sale.getTotal_paid() == null){
            throw new MiException("Valor nulo");
        }
        if(sale.getUsuario() == null){
           throw new MiException("usuario nulo");  
        }
        if(sale.getDate_sale() == null){
           throw new MiException("fecha vacia"); 
        }
    }

    
    
    
}
