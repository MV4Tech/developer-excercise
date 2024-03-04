package com.cloudruid.cloudruid.service;


import com.cloudruid.cloudruid.model.Product;

import java.util.List;

public interface ProductService {


    void addProduct(Product product);

    List<Product> fetchAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);


}
