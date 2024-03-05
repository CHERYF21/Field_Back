package com.example.field_market.repositorios;

import com.example.field_market.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Katerine
 */
public interface UsuarioRepository  extends JpaRepository <Usuario, String> {
    
    Optional<Usuario> findByUsername(String username);
    
    
    
    }
