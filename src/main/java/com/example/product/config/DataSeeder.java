package com.example.product.config;

import com.example.product.model.Category;
import com.example.product.model.Subcategory;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.SubcategoryRepository;
import com.example.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(CategoryRepository categoryRepo,
                                      SubcategoryRepository subcategoryRepo,
                                      ProductRepository productRepo) {
        return args -> {
            Category electronics = new Category();
            electronics.setName("Electronics");
            electronics.setDescription("All electronic devices");
            categoryRepo.save(electronics);

            Subcategory smartphones = new Subcategory();
            smartphones.setName("Smartphones");
            smartphones.setDescription("Mobile devices with smart features");
            smartphones.setCategory(electronics);
            subcategoryRepo.save(smartphones);

            Product iphone = new Product();
            iphone.setName("iPhone 16");
            iphone.setDescription("Latest iPhone model");
            iphone.setSubcategory(smartphones);
            productRepo.save(iphone);

            System.out.println("Data seeding completed!");
        };
    }
}