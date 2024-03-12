/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Cheryf
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpinionRequest {
   //  private String id_opinion;
     private String opinion;
     private int rating;
     private String usuario;
     private String product;
}
