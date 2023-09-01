package su.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.gamestore.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    int countByEmail(String email);
    int countByFullName(String fullName);

   Optional<User> findByEmailAndPassword(String email, String password);
}
