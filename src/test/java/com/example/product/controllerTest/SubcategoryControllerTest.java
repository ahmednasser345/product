package com.example.product.controllerTest;

import com.example.product.controller.SubCategoryController;
import com.example.product.model.Subcategory;
import com.example.product.repository.SubcategoryRepository;
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

@WebMvcTest(SubCategoryController.class)
public class SubcategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubcategoryRepository subcategoryRepository;

    @BeforeEach
    public void setUp() {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(1L);
        subcategory.setName("Smartphones");
        subcategory.setDescription("Mobile devices");

        given(subcategoryRepository.findAll()).willReturn(List.of(subcategory));
        given(subcategoryRepository.findByNameContaining("Smart")).willReturn(List.of(subcategory));
        given(subcategoryRepository.findById(1L)).willReturn(java.util.Optional.of(subcategory));
        given(subcategoryRepository.findById(99L)).willReturn(java.util.Optional.empty());
    }

    @Test
    public void testGetAllSubcategories() throws Exception {
        mockMvc.perform(get("/api/subcategories/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Smartphones")));
    }

    @Test
    public void testSearchSubcategories() throws Exception {
        mockMvc.perform(get("/api/subcategories/search")
                        .param("name", "Smart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Smartphones")));
    }

    @Test
    public void testGetSubcategoryById() throws Exception {
        mockMvc.perform(get("/api/subcategories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Smartphones")));
    }

    @Test
    public void testGetSubcategoryByInvalidId() throws Exception {
        mockMvc.perform(get("/api/subcategories/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllSubcategoriesWhenEmpty() throws Exception {
        given(subcategoryRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/subcategories/all"))
                .andExpect(status().isNotFound());
    }
}