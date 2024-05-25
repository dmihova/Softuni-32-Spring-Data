package org.softuni.services;


import org.softuni.entities.Category;
import org.softuni.repositories.CategoryRepository;
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

        Set<Long> categoriesId = new HashSet<>();

        for(int i = 0; i < maxSearch; i++){
            Long nextId = random.nextLong(  size) + 1;
            categoriesId.add(nextId);
        }

        List<Category> allById = this.categoryRepository.findAllById(categoriesId);

        return new HashSet<>(allById);
    }
}
