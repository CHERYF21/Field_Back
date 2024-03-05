/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Category;
import com.example.field_market.servicios.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cheryf
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class CategoryController {
    
    private final CategoryService categoryService;
    
    
    //crear una categoria
    @PostMapping("/createcategory")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try{
            Category newCategory = categoryService.CreateCategory(
                    category.getCategory()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        }catch (IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    //listar categorias
    @GetMapping("/listCategory")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    
    
}
