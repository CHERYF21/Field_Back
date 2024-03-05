
package com.example.field_market.servicios;

import com.example.field_market.entidades.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.field_market.repositorios.ProductRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

   public Product createProduct(
   String title, String description, double price,
   MultipartFile file, // Cambia el tipo del parámetro
   String category, boolean availability,
   int quantity
) throws IOException {
    // Crear un nuevo objeto Product con los parámetros proporcionados
    Product newProduct = new Product();
    newProduct.setTitle(title);
    newProduct.setDescription(description);
    newProduct.setPrice(price);
    
    
    // Obtener el nombre del archivo (puedes personalizar esto según tu lógica)
    String fileName = file.getOriginalFilename();
    newProduct.setImg(fileName);
    
    // Convertir la imagen a base64 y asignarla al campo base64Image
String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
newProduct.setBase64Image(base64Image);

    newProduct.setCategory(category);
    newProduct.setAvailability(availability);
    newProduct.setQuantity(quantity);

    // Validar el nuevo producto
    validateProduct(newProduct);

    // Guardar el nuevo producto en el repositorio
    return productRepository.save(newProduct);
}

  public Product updateProduct(String id, Product updatedProduct) {
    validateProduct(updatedProduct);

    Optional<Product> existingProduct = productRepository.findById(id);
    if (existingProduct.isPresent()) {
        Product productToUpdate = existingProduct.get();
        System.out.println("Updating product with ID: " + id);

        // Actualiza solo los campos no nulos en updatedProduct
        BeanUtils.copyProperties(updatedProduct, productToUpdate, getNullPropertyNames(updatedProduct));

        return productRepository.save(productToUpdate);
    } else {
        System.out.println("Product with ID " + id + " not found.");
        throw new IllegalArgumentException("El producto no existe.");
    }
}

// Método auxiliar para obtener los nombres de propiedades nulas
private String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
        Object srcValue = src.getPropertyValue(pd.getName());
        if (srcValue == null) emptyNames.add(pd.getName());
    }

    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
}



    public void deleteProduct(String id) {
    Optional<Product> productToDelete = productRepository.findById(id);
    
    if (productToDelete.isPresent()) {
        productRepository.deleteById(id);
    } else {
        // Agrega logs para depurar
        System.out.println("Intento de eliminar un producto que no existe. ID: " + id);
        throw new IllegalArgumentException("El producto no existe.");
    }
}


  // Método de validación general para un producto
    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        // Puedes agregar más validaciones según tus necesidades
        if (product.getTitle() == null || product.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("El título del producto no puede estar vacío");
        }
        
    }

    // Actualizar los campos del producto existente con los valores no nulos del producto actualizado
    private void updateProductFields(Product existingProduct, Product updatedProduct) {
        if (updatedProduct.getTitle() != null) {
            existingProduct.setTitle(updatedProduct.getTitle());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        
    }

}