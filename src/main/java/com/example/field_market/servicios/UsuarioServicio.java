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
import com.example.field_market.repositorios.UsuarioRepository;
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
    
    public UserResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return UserResponse.builder()
                .token(token)
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
              user.setRol(Rol.Agricultor);

            usuarioRepository.save(user);
            
            return UserResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
    }
    
}