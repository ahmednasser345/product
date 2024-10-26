package com.example.product.controllerTest;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setId(1L);
        product.setName("iPhone 14");
        product.setDescription("Latest iPhone model");

        given(productRepository.findAll()).willReturn(List.of(product));
        given(productRepository.findByNameContaining("iPhone")).willReturn(List.of(product));
        given(productRepository.findById(1L)).willReturn(java.util.Optional.of(product));
        given(productRepository.findById(99L)).willReturn(java.util.Optional.empty());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("iPhone 14")));
    }



    @Test
    public void testSearchProducts() throws Exception {
        mockMvc.perform(get("/api/products/search")
                        .param("name", "iPhone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("iPhone 14")));
    }

    @Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("iPhone 14")));
    }

    @Test
    public void testGetProductByInvalidId() throws Exception {
        mockMvc.perform(get("/api/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllProductsWhenEmpty() throws Exception {
        given(productRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isNotFound());
    }
}