package com.example.field_market.repositorios;

import com.example.field_market.entidades.Product;
import com.example.field_market.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

 
public interface ProductRepository extends JpaRepository<Product, String> {
    //listar productos
    List<Product> findByCategory(String category);
    //eliminar un producto por el usuario
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.usuario = :usuario")
    void deleteByUsuario(Usuario usuario);

 

}