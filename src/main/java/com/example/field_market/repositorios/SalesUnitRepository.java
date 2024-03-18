/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.field_market.repositorios;

import com.example.field_market.entidades.Sales_unit;
import com.example.field_market.entidades.Usuario;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cheryf
 */
public interface SalesUnitRepository extends JpaRepository <Sales_unit, String> {
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Sales_unit u WHERE u.usuario = :usuario")
    void deleteByUsuario(Usuario usuario);
}
