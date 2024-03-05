/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.controladores;

import com.example.field_market.entidades.Opinion;
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

/**
 *
 * @author Cheryf
 */
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class OpinionController {
    
    private final OpinionService opinionService;
    
    //crear comentario
    @PostMapping("/createOpinion")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Opinion> createdOpinion (@RequestBody Opinion opinion){
      Opinion createOpinion = opinionService.createOpinion(opinion);
      return new ResponseEntity<>(createOpinion, HttpStatus.CREATED);
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
    public ResponseEntity<String> updateSdetail(@PathVariable("id_opinion") String id_opinion, @RequestBody Opinion updatedOpinion) {
        String result = opinionService.updateOpinion(id_opinion, updatedOpinion);
        if (result.startsWith("la opinion con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    //eliminar opinion
    @DeleteMapping("/{id_opinion}")
     @CrossOrigin(origins = "http://localhost:3000")
     public ResponseEntity<String> deleteSale(@PathVariable("id_opinion") String id_opinion) {
        String result = opinionService.deleteOpinion(id_opinion);
        if (result.startsWith("la opinion con el ID")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
}
