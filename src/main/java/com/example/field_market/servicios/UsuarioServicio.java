/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.LoginRequest;
import com.example.field_market.entidades.RegisterRequest;
import com.example.field_market.entidades.Rol;
import com.example.field_market.entidades.UserResponse;
import com.example.field_market.entidades.Usuario;
import com.example.field_market.repositorios.OpinionRepository;
import com.example.field_market.repositorios.ProductRepository;
import com.example.field_market.repositorios.SaleRepository;
import com.example.field_market.repositorios.SalesUnitRepository;
import com.example.field_market.repositorios.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicio {
    
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ProductRepository productRepository;
    private final OpinionRepository opinionrepository;
    private final SaleRepository saleRepository;
    private final SalesUnitRepository unitRepository;
    
    public UserResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userdetails=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        Usuario usuario=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(userdetails);
        return UserResponse.builder()
                .token(token)
                .usuario(usuario)
                .build();
                
    }
    
    public UserResponse register(RegisterRequest request){
        Usuario user = new Usuario();
              user.setUsername(request.getUsername());
              //user.setPassword(passwordEncoder.encode(request.getPassword()));
              user.setPassword(passwordEncoder.encode(request.getPassword()));
              user.setApellido(request.getApellido());
              user.setNombre(request.getNombre());
              user.setDireccion(request.getDireccion());
              user.setTelefono(request.getTelefono());
              user.setRol(request.getRol());

            usuarioRepository.save(user);
            
            return UserResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
    }
    
    public List<Usuario> listUser(){
        return usuarioRepository.findAll();
    }
    
    public UserResponse verify(String token) {
        String userName = jwtService.getUsernameFromToken(token);
        Usuario user = usuarioRepository.findByUsername(userName).orElseThrow();
        return UserResponse.builder()
                .token(jwtService.getToken(user))
                .usuario(user)
                .build();
    }
    // eliminar usuario y productos asociados a el
    @Transactional
    public void deleteUser(String id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            usuarioRepository.deleteById(id);
        }
    }
    
}