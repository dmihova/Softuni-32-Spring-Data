package su.lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import su.lab.models.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountById(Long Id);
}
