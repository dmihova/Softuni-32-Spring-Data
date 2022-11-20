package su.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import su.user.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailEndingWith(String email);

    List<User> findByLastTimeLoggedInIsBefore(LocalDate lastLoggedInBefore);

    @Transactional
    void deleteAllByIsDeleted(boolean IsDeleted);
}
