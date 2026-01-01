package br.com.prospertech.case_java.Controller;

import br.com.prospertech.case_java.DTO.ProductDTO;
import br.com.prospertech.case_java.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        productDTO = new ProductDTO(1L, "Test Product", 99.99, "Test Description");
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        // Arrange
        List<ProductDTO> products = Arrays.asList(
                productDTO,
                new ProductDTO(2L, "Product 2", 49.99, "Description 2")
        );
        when(service.getAllProducts()).thenReturn(products);

        // Act
        List<ProductDTO> result = controller.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(service, times(1)).getAllProducts();
    }

    @Test
    void getById_WhenProductExists_ShouldReturnProduct() {
        // Arrange
        when(service.getProductById(1L)).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = controller.getById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
        verify(service, times(1)).getProductById(1L);
    }

    @Test
    void getById_WhenProductDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(service.getProductById(1L)).thenReturn(null);

        // Act
        ResponseEntity<ProductDTO> response = controller.getById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(service, times(1)).getProductById(1L);
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() {
        // Arrange
        ProductDTO newProduct = new ProductDTO(null, "New Product", 29.99, "New Description");
        ProductDTO createdProduct = new ProductDTO(1L, "New Product", 29.99, "New Description");
        when(service.createProduct(any(ProductDTO.class))).thenReturn(createdProduct);

        // Act
        ResponseEntity<ProductDTO> response = controller.createProduct(newProduct);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProduct, response.getBody());
        verify(service, times(1)).createProduct(newProduct);
    }

    @Test
    void updateProduct_WhenProductExists_ShouldReturnUpdatedProduct() {
        // Arrange
        ProductDTO updatedProduct = new ProductDTO(1L, "Updated Product", 59.99, "Updated Description");
        when(service.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(Optional.of(updatedProduct));

        // Act
        ResponseEntity<ProductDTO> response = controller.updateProduct(1L, updatedProduct);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
        verify(service, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    void updateProduct_WhenProductDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        ProductDTO updatedProduct = new ProductDTO(1L, "Updated Product", 59.99, "Updated Description");
        when(service.updateProduct(eq(1L), any(ProductDTO.class))).thenReturn(Optional.empty());

        // Act
        ResponseEntity<ProductDTO> response = controller.updateProduct(1L, updatedProduct);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(service, times(1)).updateProduct(eq(1L), any(ProductDTO.class));
    }

    @Test
    void deleteProduct_WhenProductExists_ShouldReturnNoContent() {
        // Arrange
        when(service.deleteProduct(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = controller.deleteProduct(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(service, times(1)).deleteProduct(1L);
    }

    @Test
    void deleteProduct_WhenProductDoesNotExist_ShouldReturnNotFound() {
        // Arrange
        when(service.deleteProduct(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = controller.deleteProduct(1L);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(service, times(1)).deleteProduct(1L);
    }
}
