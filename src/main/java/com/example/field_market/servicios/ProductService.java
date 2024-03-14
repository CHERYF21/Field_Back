
package com.example.field_market.servicios;


import com.example.field_market.entidades.Product;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.repositorios.CategoryRepository;
import org.springframework.stereotype.Service;
import com.example.field_market.repositorios.ProductRepository;
import com.example.field_market.repositorios.SalesUnitRepository;
import com.example.field_market.repositorios.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    SalesUnitRepository saleUnitRepository;
    
    
    @Transactional
    public void CreateProduct(Product product) throws MiException{
    
        valited(product);
        productRepository.save(product);

    }
    
    public void updateProduct(String id_product, Product updateProduct) throws MiException{
        Optional<Product> respuesta = productRepository.findById(id_product);
        
        if(respuesta.isPresent()){
            Product product = respuesta.get();
            
               product.setTitle(updateProduct.getTitle());
               product.setDescripcion(updateProduct.getDescripcion());
               product.setImg(updateProduct.getImg());
               product.setQuantity(updateProduct.getQuantity());
               product.setPrice(updateProduct.getPrice());
               product.setCategory(updateProduct.getCategory());
               product.setSales_unit(updateProduct.getSales_unit());
               productRepository.save(product);
            } else {
            throw new MiException("Producto no encontrado");
        }
       }
    
    //listproduct
    public List<Product> listProduct(){
        return productRepository.findAll();
    }
    
    //list por id
    public Optional<Product> findByIdProduct(String id){
        return productRepository.findById(id);
    }
    
    //save product
    public void saveProduct(Product product){
           throw new UnsupportedOperationException("Producto guardado"); 
    }
    
    public void valited(Product product) throws MiException {
        
          if (product.getTitle()== null ||  product.getTitle().isEmpty()) {
              throw new MiException("El campo no puede estar nulo o vacio");
          }
          if (product.getPrice() <= 0 || product.getPrice() == null) {
            throw new MiException("mayor a cero");
        }
          if (product.getDescripcion() == null || product.getDescripcion().isEmpty()) {
              throw new MiException("El campo no puede estar nulo o vacio");
          }
          if (product.getQuantity() <=0 || product.getQuantity() == null) {
              throw new MiException("El campo no puede estar nulo o vacio");
          }
 
          if (product == null) {
              throw new MiException("El campo no puede estar nulo o vacio");
          }
      }
    
    //Delete
    public void deleteProduct(String id_product){
        Optional <Product> productDelete = productRepository.findById(id_product);
        if(productDelete.isPresent()){
            productRepository.deleteById(id_product);
        } else {
            throw new IllegalArgumentException("el porducto no existe");
        }
    }
  
  }

