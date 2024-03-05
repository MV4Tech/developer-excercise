package com.cloudruid.cloudruid.service;
import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.repository.ProductRepository;
import com.cloudruid.cloudruid.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductServiceTest {

    /**
     * The product service
     */
    private ProductService productService;

    /**
     * The product repository
     */
    @Mock
    private ProductRepository productRepository;

    /**
     * Open mock annotations before each test
     * Before each test initialize product service with implementation of ProductServiceImpl
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    /**
     * Test successfully adding product to the database
     */
    @Test
    @DisplayName("Add product to the database")
    public void should_successfully_add_product() {
        // Given
        Product product = new Product();
        product.setName("Test Product");
        product.setCloud(10);

        // Mock behavior
        when(productRepository.save(product)).thenReturn(product);

        // When
        productService.addProduct(product);

        // Then
        verify(productRepository).save(product);
    }

    /**
     * Test successfully fetching all products from the database
     */
    @Test
    @DisplayName("Fetch all products from the database")
    public void should_successfully_fetch_all_products() {

        // Given

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Test Product 1");
        product1.setCloud(10);
        Product product2 = new Product();
        product2.setName("Test Product 2");
        product2.setCloud(20);
        products.add(product1);
        products.add(product2);
        //Mock behaviour
        Mockito.when(productRepository.findAll()).thenReturn(products);

        // When
        productService.fetchAllProducts();

        // Then
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    /**
     * Test successfully fetching product by id from the database
     */
    @Test
    @DisplayName("Fetch product by id from the database")
    public void should_successfully_fetch_product_by_id() {
        // Given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");
        product.setCloud(10);

        productRepository.save(product);
        // Mock behavior
        when(productRepository.findById(id)).thenReturn(java.util.Optional.of(product));

        // When
        productService.getProductById(id);

        // Then
        Mockito.verify(productRepository, Mockito.times(1)).findById(1L);
    }

    /**
     * Test successfully deleting product by id from the database
     */
    @Test
    @DisplayName("Delete product by id from the database")
    public void should_successfully_delete_product_by_id() {
        // Given
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");
        product.setCloud(10);

        productRepository.save(product);
        // Mock behavior
        when(productRepository.findById(id)).thenReturn(java.util.Optional.of(product));
        // When
        productService.deleteProduct(id);
        // Then
        Mockito.verify(productRepository, Mockito.times(1)).delete(product);
    }
}
