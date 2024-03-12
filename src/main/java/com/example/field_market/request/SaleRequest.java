/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.request;

import java.util.Date;
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
public class SaleRequest {
   // private String id_sale;
    private Date date_sale; 
    private int total_paid;
    private String id;
}
