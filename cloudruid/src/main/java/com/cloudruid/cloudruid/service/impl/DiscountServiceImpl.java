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


    // Calculate the cost of 3 for 2 discount
    @Override
    public double calculate3For2Discount(List<Product> inputProducts) {
        logger.info("I am in 3 for 2");
        List<Product> productsForDiscount = inputProducts.subList(0, 3);
        productsForDiscount.sort(Comparator.comparing(Product::getCloud));
        double cost = productsForDiscount.get(0).getCloud();
        return cost/100;
    }

    // Calculate the cost of 1 second half price discount
    @Override
    public double calculate1SecondHalfPriceDiscount(List<Product> inputProducts) {
        Map<String, Integer> productCounts = new HashMap<>(); // Map to store product counts
        double totalDiscount = 0;

        for (Product product : inputProducts) {
            String productName = product.getName();
            int cloud = product.getCloud();

            // Increment the count for the product
            int count = productCounts.getOrDefault(productName, 0) + 1;
            productCounts.put(productName, count);

            // If it's the second occurrence, calculate and add the discount
            if (count == 2) {
                totalDiscount += cloud / 2.0;
            }
        }

        return totalDiscount/100;
    }


    // calculate the cost of 1 second half price after 3 for 2 discount
    // inputProducts list must start after 3rd product
    public double calculate1SecondHalfPriceDiscountForAllDiscounts(List<Product> inputProducts) {

        List<Product> listOfProductsAfter2for3 = inputProducts.subList(3,inputProducts.size());

        Map<String, Integer> productCounts = new HashMap<>(); // Map to store product counts
        double totalDiscount = 0;

        for (Product product : listOfProductsAfter2for3) {
            String productName = product.getName();
            int cloud = product.getCloud();

            // Increment the count for the product
            int count = productCounts.getOrDefault(productName, 0) + 1;
            productCounts.put(productName, count);

            // If it's the second occurrence, calculate and add the discount
            if (count == 2) {
                totalDiscount += cloud / 2.0;
            }
        }

        return totalDiscount/100;
    }


    // Calculate the cost with all discounts
    @Override
    public double calculateWithAllDiscount(List<Product> inputProducts) {
            double costDiscount3for2 = calculate3For2Discount(inputProducts);
            double costDiscount1SecondHalfPrice = calculate1SecondHalfPriceDiscountForAllDiscounts(inputProducts);
            return costDiscount3for2 + costDiscount1SecondHalfPrice;
    }
}
