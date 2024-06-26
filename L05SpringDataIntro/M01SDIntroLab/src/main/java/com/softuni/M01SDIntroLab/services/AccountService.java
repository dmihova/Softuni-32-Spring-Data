package com.softuni.M01SDIntroLab.services;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal amount, Long id);

    void depositMoney(BigDecimal amount, Long id);

    void transferMoney(Long accountFrom, Long accountTo, BigDecimal amount);
}
