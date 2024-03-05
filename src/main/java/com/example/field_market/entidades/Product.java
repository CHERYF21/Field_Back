package com.example.field_market.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;

    private String title;
    
    private String description;
    
    private double price;
    
    @Column(columnDefinition = "LONGTEXT")
    private String img;
    
     @Lob
    private String base64Image;  // Nuevo campo para la representación en base64 de la imagen

    private String category;
    
    private boolean availability;
    
    private int quantity;
    

    // Constructores
    public Product() {
        // Constructor vacío necesario para JPA
    }

    public Product(String id, String title, String description, double price, String img, String base64Image, String category, boolean availability, int quantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.img = img;
        this.base64Image = base64Image;
        this.category = category;
        this.availability = availability;
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {    
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getQuantity() {
        return quantity;
    }

    // Getters y setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Incrementa la cantidad del producto en la cantidad especificada.
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
    
    // Decrementa la cantidad del producto en la cantidad especificada, con una verificación para asegurar que la cantidad no sea negativa.
    //java

    public void decreaseQuantity(int amount) {
        if (this.quantity - amount >= 0) {
            this.quantity -= amount;
        } else {
            // Manejo de error 
            System.out.println("No hay suficiente cantidad para decrementar.");
        }
    }

 
}