/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.field_market.repositorios;

import com.example.field_market.entidades.Opinion;
import com.example.field_market.entidades.Usuario;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Cheryf
 */
public interface OpinionRepository extends JpaRepository <Opinion, String> {
    
    Optional<Opinion> findById(String id_opinion);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Opinion o WHERE o.usuario = :usuario")
    void deleteByUsuario(Usuario usuario);
}
