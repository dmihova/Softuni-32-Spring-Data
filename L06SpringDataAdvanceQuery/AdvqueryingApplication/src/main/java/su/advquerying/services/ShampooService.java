package su.advquerying.services;

import org.springframework.beans.factory.annotation.Autowired;
import su.advquerying.entities.Shampoo;
import su.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<Shampoo> findByBrand(String s);

    List<Shampoo> findByBrandAndSize(String brand, Size small);

    List<Shampoo> findBySize(Size size);
    List<Shampoo> findBySizeOrderByIdAsc(Size size);

    List<Shampoo> findByIngredientName(String ingredient);
    List<Shampoo> findByIngredientNameIn(List<String> names);

    List<Shampoo> findByIngredientsIn(  List<String> ingredients);

    List<Shampoo> findBySizeOrLabelId(Size size, Long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countPriceLessThan(BigDecimal price);

  //  List<Shampoo> findByAllIngredients(List<String> ingredients);

    List<Shampoo> findByIngredientCountLessThan(int count);


}
