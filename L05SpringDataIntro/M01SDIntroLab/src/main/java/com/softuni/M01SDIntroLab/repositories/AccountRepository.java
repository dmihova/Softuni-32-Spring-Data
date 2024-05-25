package com.softuni.M01SDIntroLab.repositories;

import com.softuni.M01SDIntroLab.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountById(Long Id);
}
