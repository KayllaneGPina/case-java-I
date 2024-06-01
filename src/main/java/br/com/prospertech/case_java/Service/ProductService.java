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
        return null;
    }

    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDetails) {
        return null;
    }

    public boolean deleteProduct(Long id) {
        return false;
    }

    private List<ProductDTO> convertToDto(List<Product> product) {
        return product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDescription()))
                .collect(Collectors.toList());
    }
}
