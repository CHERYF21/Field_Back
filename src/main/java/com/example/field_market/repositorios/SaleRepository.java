package com.example.field_market.repositorios;

import com.example.field_market.entidades.Sale;
import com.example.field_market.entidades.Usuario;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, String> {
 
    @Transactional
    @Modifying
    @Query("DELETE FROM Sale s WHERE s.usuario = :usuario")
    void deleteByUsuario(Usuario usuario);
}
