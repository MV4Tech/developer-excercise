package com.cloudruid.cloudruid.controller;


import com.cloudruid.cloudruid.model.Product;
import com.cloudruid.cloudruid.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    /**
     * The mock mvc
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * The product service
     */
    @MockBean
    private ProductService productService;

    /**
     * Test securing successfully adding product to the database
     */
    @Test
    @DisplayName("Add product to the database(Controller)")
    public void addProduct() throws Exception {

        // Given
        Product inputProduct = new Product();
        inputProduct.setName("Test Product");
        inputProduct.setCloud(10);

        // When
        mockMvc.perform(post("/api/v1/product/add-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputProduct)))
                .andExpect(status().isCreated());

        // Then
        Mockito.verify(productService, times(1)).addProduct(inputProduct);

    }

    /**
     * Test securing successfully deleting product from the database
     */
    @Test
    @DisplayName("Delete product from the database(Controller)")
    public void deleteProduct() throws Exception {

        // Given
        Long id = 1L;

        // When
        mockMvc.perform(delete("/api/v1/product/delete-product/{id}", id))
                .andExpect(status().isNoContent());

        // Then
        Mockito.verify(productService, times(1)).deleteProduct(id);

    }

    /**
     * Convert object to json string
     * @param object
     * @return json string
     */
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
