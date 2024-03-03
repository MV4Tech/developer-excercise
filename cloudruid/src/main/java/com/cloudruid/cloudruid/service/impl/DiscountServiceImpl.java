package com.cloudruid.cloudruid.service.impl;

import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl  implements DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);


    @Override
    public double calculate3For2Discount(List<Product> inputProducts) {
        List<Product> productsForDiscount = inputProducts.subList(0, 3);
        logger.info("Products for 3 for 2 discount: " + productsForDiscount);
        productsForDiscount.sort(Comparator.comparing(Product::getCloud));
        logger.info("Products for 3 for 2 discount sorted: " + productsForDiscount);
        double cost = productsForDiscount.get(0).getCloud();
        logger.info("Cost of the product with the lowest price: " + cost);
        return cost/100;
    }
}
