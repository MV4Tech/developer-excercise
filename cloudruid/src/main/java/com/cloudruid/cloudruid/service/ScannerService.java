package com.cloudruid.cloudruid.service;

import com.cloudruid.cloudruid.model.Product;

import java.util.List;

public interface ScannerService {

    int scanWithDiscount(List<Product> inputProducts);


    String scanWithoutDiscount(List<Product> inputProducts);

    String scanWith3For2Discount(List<Product> inputProducts);

    String scanGet1SecondHalfPrice(List<Product> inputProducts);
}
