package com.cloudruid.cloudruid.service;

import com.cloudruid.cloudruid.model.Product;

import java.util.List;

public interface DiscountService {

    double calculate3For2Discount(List<Product> inputProducts);
}
