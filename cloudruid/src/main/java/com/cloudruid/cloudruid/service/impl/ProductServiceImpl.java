package com.cloudruid.cloudruid.service.impl;

import com.cloudruid.cloudruid.dto.request.UpdateQuantityRequest;
import com.cloudruid.cloudruid.exception.ProductNotFoundException;
import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.repository.ProductRepository;
import com.cloudruid.cloudruid.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    // Add product to the database
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
        logger.info("Product added successfully");
    }

    @Override
    public List<Product> fetchAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products;
    }


    @Override
    public Product getProductById(Long id) {
        Optional<Product> OptionalProduct = productRepository.findById(id);
        if (OptionalProduct.isPresent()) {
            return OptionalProduct.get();
        } else {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
        logger.info("Product with id: " + id + " deleted successfully");
    }


}
