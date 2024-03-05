/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.field_market.repositorios;

import com.example.field_market.entidades.Opinion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cheryf
 */
public interface OpinionRepository extends JpaRepository <Opinion, String> {
    
    Optional<Opinion> findById(String id_opinion);
}
