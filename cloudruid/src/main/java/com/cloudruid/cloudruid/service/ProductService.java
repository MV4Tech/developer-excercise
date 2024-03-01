package com.cloudruid.cloudruid.service;

import com.cloudruid.cloudruid.dto.request.UpdateQuantityRequest;
import com.cloudruid.cloudruid.model.Product;

public interface ProductService {
    void addProduct(Product product);

    void addQuantity(Long id, UpdateQuantityRequest request);

    Product getProductById(Long id);

    void deleteProduct(Long id);
}
