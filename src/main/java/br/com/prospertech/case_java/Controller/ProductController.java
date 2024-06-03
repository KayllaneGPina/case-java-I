package br.com.prospertech.case_java.Controller;

import br.com.prospertech.case_java.DTO.ProductDTO;
import br.com.prospertech.case_java.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = service.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<ProductDTO> updatedProduct = service.updateProduct(id, productDTO);
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
