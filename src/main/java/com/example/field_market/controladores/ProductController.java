package com.example.field_market.controladores;

import com.example.field_market.servicios.ProductService;
import com.example.field_market.entidades.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;

@RestController
@RequestMapping("/user")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/listProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();

            // Desactivar la caché para la respuesta
            CacheControl cacheControl = CacheControl.noCache().mustRevalidate();

            return ResponseEntity.ok()
                    .cacheControl(cacheControl)
                    .body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/update")
@CrossOrigin(origins = "http://localhost:3000")
public ResponseEntity<Product> updateProduct(
        @PathVariable String id,
        @RequestParam(value = "file", required = false) MultipartFile file,
        @ModelAttribute Product updatedProduct) {
    try {
        System.out.println("Updating product with ID: " + id);

        // Luego, utiliza productService.updateProduct para realizar la actualización
        Product updated = productService.updateProduct(id, updatedProduct);

        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    } catch (Exception e) {
        // Maneja las excepciones aquí si es necesario
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            System.out.println("Producto eliminado con ID: " + id); // Nuevo log
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error al eliminar el producto con ID: " + id); // Nuevo log
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/createProduct")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createProduct(
            @RequestPart("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("availability") boolean availability,
            @RequestParam("quantity") int quantity) {
        try {
            Product addedProduct = productService.createProduct(
                    title,
                    description,
                    price,
                    file,
                    category, // Utiliza directamente el parámetro 'category'
                    availability,
                    quantity
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear el producto: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error interno al procesar la solicitud", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

}