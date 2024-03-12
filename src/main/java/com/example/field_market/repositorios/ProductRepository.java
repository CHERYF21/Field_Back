package com.example.field_market.repositorios;

import com.example.field_market.entidades.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

 
public interface ProductRepository extends JpaRepository<Product, String> {


    List<Product> findByCategory(String category);


 

}