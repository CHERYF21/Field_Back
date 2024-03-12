package com.example.field_market.entidades;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id_product;
    @Lob
    private byte[] img;
    private String descripcion;
    private Double price;
    private Integer quantity;
    private String title;
    
    @ManyToOne
    @JoinColumn(name="usuario")
    Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="categoria")
    Category category;

}

