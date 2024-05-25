package org.softuni.repositories;

import org.softuni.entities.Ingredient;
import org.softuni.entities.Shampoo;
import org.softuni.entities.Size;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findByBrand(String brand);


    List<Shampoo> findBySize(Size size);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findBySizeOrderByIdAsc(Size size);

    List<Shampoo> findByIngredients_Name(String name);

    List<Shampoo> findByIngredients_NameIn(Collection<String> names);

    List<Shampoo> findBySizeOrLabel_TitleOrderByPriceDesc(Size size, String title);

    @Query(value = "SELECT s FROM Shampoo As s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name = :name")
    List<Shampoo> findByIngredientNameQry(@Param(value ="name") String ingredient);

    @Query(value = "SELECT s FROM Shampoo As s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")
    List<Shampoo> findByIngredientNameInQry( List<String> ingredients);

    List<Shampoo> findBySizeOrLabel_IdOrderByPriceDesc(Size size, Long id);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    long countByPriceLessThan(BigDecimal price);



    @Query("SELECT s FROM Shampoo AS s " +
               " WHERE s.ingredients.size = :size"  )
    List<Shampoo> findByIngredientsCount(@Param(value ="size")int count);
    @Query("SELECT s FROM Shampoo AS s " +
            " WHERE s.ingredients.size < :size" )
    List<Shampoo> findByIngredientsCountLess(@Param(value ="size")int count);

    @Query(  "SELECT s FROM Shampoo AS s "+
            " ORDER BY SIZE(s.ingredients) DESC "
    )
    List<Shampoo> findFirstOrderByIngredientsSizeDesc( );



}
