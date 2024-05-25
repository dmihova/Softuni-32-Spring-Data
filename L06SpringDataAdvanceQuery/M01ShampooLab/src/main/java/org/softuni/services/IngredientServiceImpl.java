package org.softuni.services;

import org.softuni.entities.Ingredient;
import org.softuni.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    @Override
    public List<Ingredient> findByNameStartsWith(String name) {
        return ingredientRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Ingredient> findByNameInOrderByPriceDesc(List<String> names) {
        return ingredientRepository.findByNameInOrderByPriceDesc(names);
    }

    @Override
    @Transactional
    public void deleteByName(String ingredient) {
        ingredientRepository.deleteByName(ingredient);
    }

    @Override
    @Transactional
    public void updateAllPrice() {
        ingredientRepository.updateAllPrice();
    }

    @Override
    @Transactional
    public void updatePriceWhereNameIn(BigDecimal increase, List<String> ingredients) {
        ingredientRepository.updatePriceWhereNameIn(increase,ingredients);
    }


}
