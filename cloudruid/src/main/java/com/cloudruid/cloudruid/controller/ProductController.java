package com.cloudruid.cloudruid.controller;


import com.cloudruid.cloudruid.dto.request.UpdateQuantityRequest;
import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {


    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
