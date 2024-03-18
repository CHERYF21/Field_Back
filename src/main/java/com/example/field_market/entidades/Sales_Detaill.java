/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.field_market.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Cheryf
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sales_Detaill {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    @Column(name = "id_detail")
    private String id_detail;
    
    @Column(name = "cantidad")
    private int quantity;
    
     @Column(name = "valor_unitario")
    private int unit_value;
     
     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "venta")
     Sale sale;
     
     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "product")
     Product product;
}
