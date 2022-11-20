package su.advquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.advquerying.entities.Ingredient;
import su.advquerying.entities.Shampoo;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository  extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByName(String name);
    List<Ingredient> findByNameStartsWith(String name);

    List<Ingredient> findByNameIn(List<String> names);

    List<Ingredient> findByNameInOrderByPrice(List<String> ingredients);

    void deleteByName(String name);

    @Query("UPDATE Ingredient AS i SET i.price= i.price*1.1")
    @Modifying
    void updateAllPrice();

    @Query("UPDATE Ingredient AS i SET i.price= i.price + i.price*:increase "+
    " WHERE i.name in :names")
    @Modifying
    void updatePriceWhereNameIn(BigDecimal increase,List <String> names);
}
