/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Sale;
import com.example.field_market.repositorios.SaleRepository;
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
public class SaleService {
    
    private final SaleRepository saleRepository;
    
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Obtener una venta por su ID
    public Optional<Sale> getSaleById(String id_sale) {
        return saleRepository.findById(id_sale);
    }

    // Actualizar una venta
    public String updateSale(String id_sale, Sale updatedSale) {
    Optional<Sale> optionalSale = saleRepository.findById(id_sale);

    if (optionalSale.isPresent()) {
        Sale existingSale = optionalSale.get();
        existingSale.setDate_sale(updatedSale.getDate_sale());
        existingSale.setTotal_paid(updatedSale.getTotal_paid());
        existingSale.setUsuario(updatedSale.getUsuario());
        saleRepository.save(existingSale);
        return "Venta actualizada exitosamente.";
    } else {
        return "La venta con el ID " + id_sale + " no existe.";
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
    
    
    
}
