package org.softuni.services;


import org.softuni.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> findByName(String name);

    List<Ingredient> findByNameStartsWith(String name);

    List<Ingredient> findByNameInOrderByPriceDesc(List<String> ingredients);

    void deleteByName(String ingredient);

    void updateAllPrice();

    void updatePriceWhereNameIn(BigDecimal increase, List<String> ingredients);
}

