package com.softuni.services;

import com.softuni.models.entities.Category;
import com.softuni.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = categoryRepository.count();
        long maxSearch  = Math.max(3,size);

        Random random = new Random();

        int categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categoriesId = new HashSet<>();

        for(int i = 0; i < maxSearch; i++){
            int nextId = random.nextInt((int) size) + 1;
            categoriesId.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesId);

        return new HashSet<>(allById);
    }
}
