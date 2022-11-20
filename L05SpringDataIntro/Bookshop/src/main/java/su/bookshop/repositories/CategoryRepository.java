package su.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import su.bookshop.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}