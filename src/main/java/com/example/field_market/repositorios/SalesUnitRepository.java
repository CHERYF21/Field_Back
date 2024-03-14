/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.field_market.repositorios;

import com.example.field_market.entidades.Sales_unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cheryf
 */
public interface SalesUnitRepository extends JpaRepository <Sales_unit, String> {
    
}
