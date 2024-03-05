/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.servicios;

import com.example.field_market.entidades.Opinion;
import com.example.field_market.repositorios.OpinionRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cheryf
 */
@Service
@RequiredArgsConstructor
public class OpinionService {
    
    private final OpinionRepository opinionRepository;
    
    //guadar opinion
    public Opinion createOpinion(Opinion opinion){
        return opinionRepository.save(opinion);
    }
    
    //listar opiniones
    public List<Opinion> getAllOpinion(){
        return opinionRepository.findAll();
    }
    
    //opinion por su id
    public Optional<Opinion> getOpinionById(String id_opinion){
       return opinionRepository.findById(id_opinion);
    }
    
    //actualizar Opinion
    public String updateOpinion(String id_opinion, Opinion updateOpinion){
        Optional<Opinion> optionalOpinion = opinionRepository.findById(id_opinion);
        if(optionalOpinion.isPresent()){
            Opinion opinion = optionalOpinion.get();
            opinion.setOpinion(updateOpinion.getOpinion());
            opinion.setRating(updateOpinion.getRating());
            opinion.setProduct(updateOpinion.getProduct());
            opinion.setUsuario(updateOpinion.getUsuario());
            opinionRepository.save(opinion);
            return "Opinion actualizada con exito";   
        }else{
            return "La opinion con ID: " + id_opinion + "no existe";
        }
    }
    
    
    //eliminar Opinion
    public String deleteOpinion(String id_opinion){
        Optional<Opinion> optionalOpinion = opinionRepository.findById(id_opinion);
        
        if(optionalOpinion.isPresent()){
           opinionRepository.deleteById(id_opinion);
           return "Opinion eliminada con exito";
        }else{
            return "La Opinion con ID: " + id_opinion + "no existe";
        }
    }
    
}
