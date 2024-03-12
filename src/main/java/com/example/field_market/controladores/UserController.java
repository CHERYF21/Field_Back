package com.example.field_market.controladores;

import com.example.field_market.entidades.LoginRequest;
import com.example.field_market.entidades.RegisterRequest;
import com.example.field_market.entidades.Sale;
import com.example.field_market.entidades.UserResponse;
import com.example.field_market.entidades.Usuario;
import com.example.field_market.servicios.UsuarioServicio;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;  
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UsuarioServicio usuarioServicio;
        
    
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request)
    {
       return ResponseEntity.ok(usuarioServicio.login(request));
    }
    
      @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) 
    {
        return ResponseEntity.ok(usuarioServicio.register(request));
    }
   
    @GetMapping("/listUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Usuario>> getAllUser(){
        List<Usuario> usuario = usuarioServicio.listUser();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


}





    
    
    //actualizar usuario
    //@PutMapping("/actualizar/{id}")
    //public ResponseEntity<?> modificarUsuario(@PathVariable String id, @RequestBody Usuario usuario){
      //try{
          //usuarioServicio.modificarUsuario(id, usuario.getEmail(), usuario.getTelefono(), 0, usuario.getPassword(), usuario.getDireccion());
        //  return ResponseEntity.ok().build();
      //} catch (MiException ex){
      //    return ResponseEntity.badRequest().body(ex.getMessage());
      //}
    //}

    //@Override
    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
        
    


