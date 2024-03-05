package com.example.field_market.repositorios;

import com.example.field_market.entidades.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

 
public interface ProductRepository extends JpaRepository<Product, String> {


    Optional<Product> findById(String id);

    List<Product> findByCategory(String category);

    List<Product> findByAvailabilityTrue();

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

 

}



/* agregar img en formato pnj y jpg en react 


Importar directamente la img
import React from 'react';
import pngImage from './images/yourImage.png';
import jpegImage from './images/yourImage.jpg';

function MyComponent() {
  return (
    <div>
      <img src={pngImage} alt="Descripción de la imagen PNG" />
      <img src={jpegImage} alt="Descripción de la imagen JPEG" />
    </div>
  );
}

export default MyComponent;

y usar la ruta directa:

import React from 'react';

function MyComponent() {
  return (
    <div>
      <img src="./images/yourImage.png" alt="Descripción de la imagen PNG" />
      <img src="./images/yourImage.jpg" alt="Descripción de la imagen JPEG" />
    </div>
  );
}

export default MyComponent;



*/
