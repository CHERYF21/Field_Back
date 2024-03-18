
package com.example.field_market.controladores;

import com.example.field_market.entidades.Category;
import com.example.field_market.entidades.Product;
import com.example.field_market.entidades.Sales_unit;
import com.example.field_market.entidades.Usuario;
import com.example.field_market.excepciones.MiException;
import com.example.field_market.servicios.ProductService;
import com.example.field_market.repositorios.CategoryRepository;
import com.example.field_market.repositorios.ProductRepository;
import com.example.field_market.repositorios.SalesUnitRepository;
import com.example.field_market.repositorios.UsuarioRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UsuarioRepository usuariorepository;
     @Autowired
    ProductRepository productRepository;
     @Autowired
    SalesUnitRepository saleUnitRepository;
     
    
    //list Products
    @PostMapping("/createProduct")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createProduct(
            @RequestParam("id_category") String id_category,
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("descripcion") String description,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Integer quantity,
             @RequestParam("id_saleUnit") String id_saleUnit,
            @RequestParam("img") MultipartFile img){
        
        try{
            Category category = categoryRepository.findById(id_category).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria no encontrada"));
            Usuario usuario = usuariorepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrada"));
            Sales_unit salesunit = saleUnitRepository.findById(id_saleUnit).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad no encontrada"));
            
            Product product = new Product();
            byte[] fileBytes = img.getBytes();
            product.setCategory(category);
            product.setUsuario(usuario);
            product.setDescripcion(description);
            product.setPrice(price);
            product.setTitle(title);
            product.setQuantity(quantity);
            product.setImg(fileBytes);
            product.setSales_unit(salesunit);
            
            productService.CreateProduct(product);
            return new ResponseEntity<>("Producto creado con exito",HttpStatus.CREATED);
        } catch (IOException ex){
            return new ResponseEntity<>("error al crear producto" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MiException ex){
            return new ResponseEntity<>("Error al crear producto : " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}/update")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateProduct(@PathVariable String id_product,@RequestParam("name") String name,
            @RequestParam("title") String title,
            @RequestParam("descripcion") String description,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("img") MultipartFile img) throws IOException{
        
        try{
            Product product = new Product();
            byte[] fileBytes = img.getBytes();
            product.setTitle(title);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setImg(fileBytes);
            
            productService.updateProduct(id_product, product);
            
            return ResponseEntity.ok("producto actualizado correctamente");
        } catch (MiException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar product" + ex.getMessage());
        }
    }
    
    //list
    @GetMapping("/listProducts")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Product> listProduct(){
        var product = productService.listProduct();
        return product;
    }
       
    @DeleteMapping("/{id_product}/delete")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteProduct(@PathVariable String id_product){
        productService.deleteProduct(id_product);
        return new ResponseEntity<>("Producto Eliminado",HttpStatus.OK);
    }

}
