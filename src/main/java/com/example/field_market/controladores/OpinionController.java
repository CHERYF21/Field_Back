/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Opinion;
import com.example.field_market.entidades.Product;
import com.example.field_market.entidades.Usuario;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.ProductRepository;
import com.example.field_market.repositorios.UsuarioRepository;
import com.example.field_market.request.OpinionRequest;
import com.example.field_market.servicios.OpinionService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Cheryf
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class OpinionController {
    
    private final OpinionService opinionService;
    private final ProductRepository productRepository;
    private final UsuarioRepository usuarioRepository;
    
    //crear comentario
    @PostMapping("/createOpinion")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createdOpinion (@RequestBody OpinionRequest request){
      try{
          String id_product = request.getProduct();
          Product product = productRepository.findById(id_product).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"));
          String id = request.getUsuario();
          Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
          
          Opinion opinion = new Opinion();
          opinion.setOpinion(request.getOpinion());
          opinion.setRating(request.getRating());
          opinion.setProduct(product);
          opinion.setUsuario(usuario);
          
          opinionService.createOpinion(opinion);
          return new ResponseEntity<>("Opinion enviada",HttpStatus.CREATED);
      } catch (ResponseStatusException ex){
          return new ResponseEntity<>("Error al enviar opinion" + ex.getMessage(),ex.getStatus());
      } catch (Exception ex){
          return new ResponseEntity<>("Error al enviar opinion: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    //listar opiniones
    @GetMapping("/listOpinion")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity <List<Opinion>> getAllOpinion(){
        List<Opinion> opinion = opinionService.getAllOpinion();
        return new ResponseEntity<>(opinion, HttpStatus.OK);
    }
    
    //obtener una opinion en especifico
     @GetMapping("/{id_opinion}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Opinion> getOpinionById(@PathVariable("id_opinion") String id_opinion){
        Optional<Opinion> opinion = opinionService.getOpinionById(id_opinion);
        return opinion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    //actualizar una opinion
    @PutMapping("/{id_opinion}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateSdetail(@PathVariable String id_opinion, @RequestBody Opinion opinion) {
        try{
            opinionService.updateOpinion(id_opinion, opinion);
            return ResponseEntity.ok("Opinion enviada con exito");
        } catch (MiException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar opinion" + ex.getMessage());
        }
    }
    
    //eliminar opinion
    //@DeleteMapping("/{id_opinion}/delete")
    @DeleteMapping("/deleteOpinion/{id_opinion}") //se cambio el de arriba por este  <--
    @CrossOrigin(origins = "http://localhost:3000")
     public ResponseEntity<String> deleteOpinion(@PathVariable("id_opinion") String id_opinion) {
        String result = opinionService.deleteOpinion(id_opinion);
        if (result.startsWith("la opinion con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
}
