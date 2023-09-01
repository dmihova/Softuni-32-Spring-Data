package su.productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.productsshop.entiies.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
