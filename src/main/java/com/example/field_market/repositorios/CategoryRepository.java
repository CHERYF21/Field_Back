/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.field_market.repositorios;

import com.example.field_market.entidades.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cheryf
 */
public interface CategoryRepository extends JpaRepository<Category, String> {
    
    Optional <Category> findById(String id_category);
    
    //List<Category> findByCategory(String category);
}
