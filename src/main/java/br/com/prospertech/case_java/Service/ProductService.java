package br.com.prospertech.case_java.Service;

import br.com.prospertech.case_java.DTO.ProductDTO;
import br.com.prospertech.case_java.Model.Product;
import br.com.prospertech.case_java.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> getAllProducts() {
        return convertToDto(repository.findAll());
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            Product p = product.get();
            return new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDescription());
        }
        return null;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDescription(productDTO.description());
        Product savedProduct = repository.save(product);
        return new ProductDTO(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), savedProduct.getDescription());
    }

    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDetails) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(productDetails.name());
                    product.setPrice(productDetails.price());
                    product.setDescription(productDetails.description());
                    Product updatedProduct = repository.save(product);
                    return new ProductDTO(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getDescription());
                });
    }

    public boolean deleteProduct(Long id) {
        return repository.findById(id)
                .map(product -> {
                    repository.delete(product);
                    return true;
                }).orElse(false);
    }

    private List<ProductDTO> convertToDto(List<Product> product) {
        return product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDescription()))
                .collect(Collectors.toList());
    }
}
