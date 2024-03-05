package com.cloudruid.cloudruid.controller;

import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ScannerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest(ScannerController.class)
public class ScannerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ScannerService scannerService;


    @Test
    @DisplayName("Scan products without discount (Controller)")
    public void scanWithoutDiscount() throws Exception {
        // Mock input data
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(40)
                .build();
        Product product2 = Product.builder()
                .name("apple")
                .cloud(50)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);

        // Mock service response
        Mockito.when(scannerService.scanWithoutDiscount(inputProducts)).thenReturn("0 aws and 90 clouds");

        // Perform GET request
        mockMvc.perform(get("/api/v1/scanner/scan-without-discount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputProducts)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Scan product with 3 for 2 discount only")
    public void scanWith2For3Discount() throws Exception {
        // Mock input data
        List<Product> inputProducts = new ArrayList<>();
        Product product1 = Product.builder()
                .name("banana")
                .cloud(120)
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

        // Mock service response
        Mockito.when(scannerService.scanWith3For2Discount(inputProducts)).thenReturn("1 aws and 70 clouds");

        // Perform GET request
        mockMvc.perform(get("/api/v1/scanner/scan-with-3for2-discount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputProducts)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Scan product with 1 second half price discount only")
    public void scanWith1SecondHalfPrice() throws Exception {
        // Mock input data
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
                .name("banana")
                .cloud(40)
                .build();
        inputProducts.add(product1);
        inputProducts.add(product2);
        inputProducts.add(product3);

        // Mock service response
        Mockito.when(scannerService.scanGet1SecondHalfPrice(inputProducts)).thenReturn("1 aws and 10 clouds");

        // Perform GET request
        mockMvc.perform(get("/api/v1/scanner/scan-get-1-second-half-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputProducts)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Scan product with all discounts")
    public void scanWithAllDiscount() throws Exception {
        // Mock input data
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

        // Mock service response
        Mockito.when(scannerService.scanWithAllDiscount(inputProducts)).thenReturn("1 aws and 99 clouds");

        // Perform GET request
        mockMvc.perform(get("/api/v1/scanner/scan-with-all-discount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputProducts)))
                .andExpect(status().isOk());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
