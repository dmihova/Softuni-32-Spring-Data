package su.productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.productsshop.entity.category.CategoryStatsDTO;
import su.productsshop.entity.product.Product;
import su.productsshop.entity.product.ProductWithoutBuyerDTO;
import su.productsshop.entity.product.ProductWithoutBuyerDTOI;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<ProductWithoutBuyerDTOI> findByBuyerNullAndPriceBetweenOrderByPrice(BigDecimal min, BigDecimal max);

    @Query("SELECT new su.productsshop.entity.product.ProductWithoutBuyerDTO(" +
            "p.name, p.price, p.seller.firstName,p.seller.lastName) " +
            " FROM Product p WHERE p.price >:rangeStart AND p.price<:rangeEnd AND p.buyer IS NULL "
    )
    List<ProductWithoutBuyerDTO> findByBuyerNullAndPriceBetweenOrderByPriceqQ(BigDecimal rangeStart, BigDecimal rangeEnd);

    @Query("select new su.productsshop.entity.category.CategoryStatsDTO(" +
            "c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c.id " +
            "ORDER BY count(p.id) DESC")
    List<CategoryStatsDTO> getCategoryStatistics();
}
