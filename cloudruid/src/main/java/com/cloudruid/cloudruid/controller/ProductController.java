package com.cloudruid.cloudruid.controller;


import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {


    private final ProductService productService;

    /**
     * Add product to the database
     * @param product
     * @return HttpStatus 201
     */
    @Operation(
            description = "Add product to the database",
            summary = "This is a summary for adding product to the database"
    )
    @PostMapping("/add-product")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Fetch all products from the database
     * @return List of products
     */
    @Operation(
            description = "Fetch all products from the database",
            summary = "This is a summary for fetching all products from the database"
    )
    @GetMapping("/fetch-all-products")
    public ResponseEntity<List<Product>> fetchAllProducts() {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }


    /**
     * Delete product from the database
     * @param id
     * @return HttpStatus 204(no content)
     */
    @Operation(
            description = "Delete product from the database",
            summary = "This is a summary for deleting product from the database"
    )
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
