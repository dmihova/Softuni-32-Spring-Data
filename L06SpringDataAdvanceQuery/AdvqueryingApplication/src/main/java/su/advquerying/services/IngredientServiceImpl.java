package su.advquerying.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.advquerying.entities.Ingredient;
import su.advquerying.repositories.IngredientRepository;
import su.advquerying.repositories.ShampooRepository;

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
   public List<Ingredient> findByNameIn(List<String> names) {
       return ingredientRepository.findByNameIn(names);
   }

    @Override
    public List<Ingredient> findByNameInOrderByPrice(List<String> ingredients) {
        return  ingredientRepository.findByNameInOrderByPrice(ingredients);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        ingredientRepository.deleteByName(name);
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
