package org.softuni.repositories;

import org.softuni.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailEndingWith(String email);

    List<User> findByLastTimeLoggedInIsBefore(LocalDate lastLoggedInBefore);

    @Transactional
    void deleteAllByIsDeleted(boolean IsDeleted);
}
