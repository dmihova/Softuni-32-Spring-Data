package su.advquerying.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import su.advquerying.entities.Shampoo;
import su.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySize(Size size);

    List<Shampoo> findBySizeOrderByIdAsc(Size size);

    @Query(value = "SELECT s FROM Shampoo As s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name = :name")
    List<Shampoo> findByIngredientName(@Param(value ="name") String ingredient);

    @Query(value = "SELECT s FROM Shampoo As s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")
    List<Shampoo> findByIngredientNameIn( List<String> ingredients);



    List<Shampoo> findByIngredientsIn( List<String> ingredients);

    List<Shampoo> findBySizeOrLabelId(Size size, Long labelId);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countByPriceLessThan(BigDecimal price);

    @Query(value = "SELECT s FROM Shampoo As s " +
              " WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientCountLessThan(int count);

}
