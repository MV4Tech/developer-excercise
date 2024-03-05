package com.cloudruid.cloudruid.service;

import com.cloudruid.cloudruid.exception.ProductNotSupportedByTheSystemException;
import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.impl.ScannerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ScannerServiceTest {

        /**
         * The scanner service
         */
        private ScannerService scannerService;

        /**
         * The discount service
         */
        @MockBean
        private DiscountService discountService;

        /**
         * The product service
         */
        @MockBean
        private ProductService productService;

        /**
         * Test if the product is supported by the system
         */
        @Test
        @DisplayName("Product Supported by the system test(Scanner Service)")
        public void should_successfully_product_supported_by_the_system() {
            // Given
             scannerService = new ScannerServiceImpl(discountService, productService);

            // Sample supported products by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"Product1", 10));
            supportedProductsByTheSystem.add(new Product(2L,"Product2", 20));
            supportedProductsByTheSystem.add(new Product(3L,"Product3", 30));

            // Sample input products
            List<Product> inputProducts = new ArrayList<>();
            inputProducts.add(new Product(1L,"Product1", 10));
            inputProducts.add(new Product(2L,"Product2", 20));
            inputProducts.add(new Product(3L,"Product3", 30));
            // When & Then
            Assertions.assertDoesNotThrow(() -> scannerService.isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem));
        }

        /**
         * Test if product is not supported by the system
         */
        @Test
        @DisplayName("Product Not Supported by the system test(Scanner Service)")
        public void should_throw_exception_when_product_not_supported_by_the_system() {
            // Given
            scannerService = new ScannerServiceImpl(discountService, productService);

            // Sample supported products by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"Product1", 10));
            supportedProductsByTheSystem.add(new Product(2L,"Product2", 20));
            supportedProductsByTheSystem.add(new Product(3L,"Product3", 30));

            // Sample input products
            List<Product> inputProducts = new ArrayList<>();
            inputProducts.add(new Product(1L,"Product1", 10));
            inputProducts.add(new Product(2L,"ProductX", 20));
            inputProducts.add(new Product(3L,"Product3", 30));
            // When & Then
            // An exception should be thrown when an unsupported product is encountered
            Assertions.assertThrows(ProductNotSupportedByTheSystemException.class, () -> {
                scannerService.isProductSupportedByTheSystem(inputProducts, supportedProductsByTheSystem);
            });
        }

        /**
         * Test successfully summing products without discount
         */
        @Test
        @DisplayName("Scan with no discounts test(Scanner Service)")
        public void should_successfully_scan_with_no_discounts() {
            // Given
            scannerService = new ScannerServiceImpl(discountService, productService);
            // Supported by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"banana", 10));
            supportedProductsByTheSystem.add(new Product(2L,"apple", 20));
            supportedProductsByTheSystem.add(new Product(3L,"potato", 30));
            Mockito.when(productService.fetchAllProducts()).thenReturn(supportedProductsByTheSystem);
            // Sample input products
            List<Product> inputProducts = new ArrayList<>();
            Product product1 = new Product();
            product1.setName("banana");
            product1.setCloud(10);
            Product product2 = new Product();
            product2.setName("apple");
            product2.setCloud(20);
            Product product3 = new Product();
            product3.setName("banana");
            product3.setCloud(30);
            inputProducts.add(product1);
            inputProducts.add(product2);
            inputProducts.add(product3);
            // When
            Double result = scannerService.sumOfProductsWithoutDiscount(inputProducts);

            // Then
            Assertions.assertEquals(0.60, result);
        }


        /**
         * Test successfully summing products with 3 for 2 discount
         */
        @Test
        @DisplayName("Scan with 3 for 2 discount test(Scanner Service)")
        public void should_successfully_scan_with_3_for_2_discount() {
            // Given
            scannerService = new ScannerServiceImpl(discountService, productService);
            // Supported by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"banana", 20));
            supportedProductsByTheSystem.add(new Product(2L,"apple", 30));
            Mockito.when(productService.fetchAllProducts()).thenReturn(supportedProductsByTheSystem);
            // Sample input products
            List<Product> inputProducts = new ArrayList<>();
            Product product1 = new Product();
            product1.setName("apple");
            product1.setCloud(30);
            Product product2 = new Product();
            product2.setName("banana");
            product2.setCloud(20);
            Product product3 = new Product();
            product3.setName("banana");
            product3.setCloud(10);
            inputProducts.add(product1);
            inputProducts.add(product2);
            inputProducts.add(product3);
            // When
            Mockito.when(discountService.calculate3For2Discount(inputProducts)).thenReturn(0.10);
            String result = scannerService.scanWith3For2Discount(inputProducts);

            // Then
            Assertions.assertEquals("0 aws and 50 clouds", result);
        }

        /**
         * Test successfully summing products with 1 second half price discount
         */
        @Test
        @DisplayName("Scan with 1 second half price discount test(Scanner Service)")
        public void should_successfully_scan_with_1_second_half_price_discount() {
            // Given
            scannerService = new ScannerServiceImpl(discountService, productService);
            // Supported by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"banana", 20));
            supportedProductsByTheSystem.add(new Product(2L,"apple", 30));
            Mockito.when(productService.fetchAllProducts()).thenReturn(supportedProductsByTheSystem);
            // Sample input products
            List<Product> inputProducts = new ArrayList<>();
            Product product1 = new Product();
            product1.setName("apple");
            product1.setCloud(280);
            Product product2 = new Product();
            product2.setName("banana");
            product2.setCloud(120);
            Product product3 = new Product();
            product3.setName("banana");
            product3.setCloud(120);
            inputProducts.add(product1);
            inputProducts.add(product2);
            inputProducts.add(product3);
            // When
            Mockito.when(discountService.calculate1SecondHalfPriceDiscount(inputProducts)).thenReturn(0.60);
            String result = scannerService.scanGet1SecondHalfPrice(inputProducts);

            // Then
            Assertions.assertEquals("4 aws and 60 clouds", result);
        }

        /**
         * Test successfully summing products with all discounts
         */
        @Test
        @DisplayName("Scan with all discounts test(Scanner Service)")
        public void should_successfully_scan_with_all_discounts() {
            // Given
            scannerService = new ScannerServiceImpl(discountService, productService);
            // Supported by the system
            List<Product> supportedProductsByTheSystem = new ArrayList<>();
            supportedProductsByTheSystem.add(new Product(1L,"banana", 20));
            supportedProductsByTheSystem.add(new Product(2L,"apple", 30));
            supportedProductsByTheSystem.add(new Product(3L,"potato", 40));
            supportedProductsByTheSystem.add(new Product(4L,"tomato", 20));
            Mockito.when(productService.fetchAllProducts()).thenReturn(supportedProductsByTheSystem);
            // Sample input products
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

            Mockito.when(discountService.calculateWithAllDiscount(inputProducts)).thenReturn(0.53);
            String result = scannerService.scanWithAllDiscount(inputProducts);

            // Then
            Assertions.assertEquals("1 aws and 99 clouds", result);
        }
}
