package com.cloudruid.cloudruid.service;

import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.impl.DiscountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    @DisplayName("Calculate 3 for 2 discount with 3 products as input")
    public void should_successfully_calculate_3_for_2_discount_with_3_products_as_input() {
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("apple")
                .cloud(50)
                .build();
        Product product3 = Product.builder()
                .name("potato")
                .cloud(30)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);
        // When
        double result = discountService.calculate3For2Discount(inputProducts);

        // Then
        assert result == 0.3;

    }

    @Test
    @DisplayName("Calculate 3 for 2 discount with more than 3 products as input")
    public void should_successfully_calculate_3_for_2_discount_with_more_than_3_products_as_input() {
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("apple")
                .cloud(50)
                .build();
        Product product3 = Product.builder()
                .name("potato")
                .cloud(30)
                .build();
        Product product4 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product5 = Product.builder()
                .name("apple")
                .cloud(50)
                .build();
        Product product6 = Product.builder()
                .name("potato")
                .cloud(30)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);
        inputProducts.add(product4);
        inputProducts.add(product5);
        inputProducts.add(product6);
        // When
        double result = discountService.calculate3For2Discount(inputProducts);

        // Then
        assert result == 0.3;

    }

    @Test
    @DisplayName("Calculate 1 second half price discount with 2 products as input")
    public void should_successfully_calculate_1_second_half_price_discount_with_2_products_as_input() {
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        // When
        double result = discountService.calculate1SecondHalfPriceDiscount(inputProducts);

        // Then
        assert result == 0.2;

    }

    @Test
    @DisplayName("Calculate 1 second half price discount with more than 2 products as input")
    public void should_successfully_calculate_1_second_half_price_discount_with_more_than_2_products_as_input() {
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product3 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        Product product4 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);
        inputProducts.add(product4);
        // When
        double result = discountService.calculate1SecondHalfPriceDiscount(inputProducts);

        // Then
        assert result == 0.33;

    }

    @Test
    @DisplayName("Calculate 1 second half price discount for all Discounts")
    public void should_successfully_calculate_1_second_half_price_discount_for_all_Discounts(){
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product3 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        Product product4 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        Product product5 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product6 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);
        inputProducts.add(product4);
        inputProducts.add(product5);
        inputProducts.add(product6);
        // When
        double result = discountService.calculate1SecondHalfPriceDiscountForAllDiscounts(inputProducts);


        // Then
        assert result == 0.13;


    }

    @Test
    @DisplayName("Calculate with all discounts")
    public void should_successfully_calculate_with_all_discounts() {
        // Given
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("apple")
                .cloud(50)
                .build();
        Product product2 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product3 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product4 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        Product product5 = Product.builder()
                .name("tomato")
                .cloud(30)
                .build();
        Product product6 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product7 = Product.builder()
                .name("potato")
                .cloud(26)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);
        inputProducts.add(product4);
        inputProducts.add(product5);
        inputProducts.add(product6);
        inputProducts.add(product7);
        // When
        double result = discountService.calculateWithAllDiscount(inputProducts);

        // Then
        assert result == 0.53;
    }
}
