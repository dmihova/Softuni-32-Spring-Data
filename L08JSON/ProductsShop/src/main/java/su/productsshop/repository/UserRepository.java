package su.productsshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.productsshop.entity.user.User;
import su.productsshop.entity.user.UserWithSoldProductsDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.itemsSold AS p" +
            " WHERE NOT p.buyer IS NOT NULL ")
    List<User> findAllWithSoldProducts();

    @Query("SELECT u FROM User u JOIN u.itemsSold AS p" +
            " WHERE NOT p.buyer IS NOT NULL "+
            " ORDER BY size(u.itemsSold) DESC, u.lastName ASC ")
    List<User> findAllWithSoldProductsOrderByCount();
}
