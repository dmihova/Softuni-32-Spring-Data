package su.advquerying.services;

import su.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> findByName(String name);
    List<Ingredient> findByNameStartsWith(String name);
    List<Ingredient> findByNameIn(List<String> names);

    List<Ingredient>findByNameInOrderByPrice(List<String> ingredients);
    void deleteByName(String name);

    void updateAllPrice();

    void updatePriceWhereNameIn(BigDecimal increase, List<String> ingredients);
}

