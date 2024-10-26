package com.example.product.controllerTest;

import com.example.product.controller.CategoryController;
import com.example.product.model.Category;
import com.example.product.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        category.setDescription("Electronic devices");

        given(categoryRepository.findAll()).willReturn(List.of(category));
        given(categoryRepository.findByNameContaining("Electronics")).willReturn(List.of(category));
        given(categoryRepository.findById(1L)).willReturn(java.util.Optional.of(category));
        given(categoryRepository.findById(99L)).willReturn(java.util.Optional.empty());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Electronics")));
    }

    @Test
    public void testSearchCategories() throws Exception {
        mockMvc.perform(get("/api/category/search")
                        .param("name", "Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Electronics")));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        mockMvc.perform(get("/api/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Electronics")));
    }

    @Test
    public void testGetCategoryByInvalidId() throws Exception {
        mockMvc.perform(get("/api/category/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCategoriesWhenEmpty() throws Exception {
        given(categoryRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/category/all"))
                .andExpect(status().isNotFound());
    }
}