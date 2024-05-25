package org.softuni.services;

import org.softuni.entities.Shampoo;
import org.softuni.entities.Size;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShampooService {

    List<Shampoo> findByBrand(String s);


    List<Shampoo>  findBySize(Size size);

    List<Shampoo>  findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderByIdAsc(Size size);

    List<Shampoo>findByIngredientsNameIn(Collection<String> names);

    List<Shampoo> findByIngredientName(String apple);

    List<Shampoo>findBySizeOrLabel_TitleOrderByPriceDesc(Size size, String label);

    List<Shampoo> findByIngredientNameQry(String name);

    List<Shampoo>  findByIngredientNameInQry(List<String> ingredients);

    List<Shampoo>  findBySizeOrLabel_IdOrderByPriceDesc(Size medium, Long labelId);

    List<Shampoo>  findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countByPriceLessThan(BigDecimal price);

    List<Shampoo>  findByIngredientsCount(int count);

    List<Shampoo>findByIngredientsCountLess(int count);

    List<Shampoo>findFirstOrderByIngredientsSizeDesc( );
}
