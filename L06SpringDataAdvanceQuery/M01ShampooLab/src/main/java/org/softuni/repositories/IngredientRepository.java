package org.softuni.repositories;

import org.softuni.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository  extends JpaRepository<Ingredient, Long> {
    long deleteByName(String name);
    List<Ingredient> findByName(String name);

    List<Ingredient> findByNameStartsWith(String name);

    List<Ingredient> findByNameInOrderByPriceDesc(Collection<String> names);


    @Query("UPDATE Ingredient AS i SET i.price= i.price*1.1")
    @Modifying
    void updateAllPrice();

    @Query("UPDATE Ingredient AS i SET i.price= i.price + i.price*:increase "+
            " WHERE i.name in :names")
    @Modifying
    void updatePriceWhereNameIn(BigDecimal increase, List<String> names);
}
