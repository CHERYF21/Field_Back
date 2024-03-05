/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Category;
import com.example.field_market.repositorios.CategoryRepository;
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
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    
    
    //Crear categoria
    public Category CreateCategory (String category){
        
        Category newCategory = new Category();
        newCategory.setCategory(category);
        
       return categoryRepository.save(newCategory);
    }
    
    //listar categorias
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    
    //actualizar category
   public Category updateCategory(String id_category, Category updateCategory){

    Optional<Category> optionalCategory = categoryRepository.findById(id_category);
    
    if (optionalCategory.isPresent()) {
        Category existingCategory = optionalCategory.get();
        existingCategory.setCategory(updateCategory.getCategory()); 
        
        return categoryRepository.save(existingCategory);
    } else {
        throw new IllegalArgumentException("la categoria no existe");
    }
}
   
   //eliminar category
    public void deleteCategory(String id_category) {
    Optional<Category> categoryToDelete = categoryRepository.findById(id_category);
    
    if (categoryToDelete.isPresent()) {
        categoryRepository.deleteById(id_category);
    } else {
        throw new IllegalArgumentException("la categoria no existe.");
    }
    
 }
    
}
