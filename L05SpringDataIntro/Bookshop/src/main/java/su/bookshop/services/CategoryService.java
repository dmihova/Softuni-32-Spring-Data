package su.bookshop.services;

import su.bookshop.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
