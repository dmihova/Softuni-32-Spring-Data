package su.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.gamestore.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
