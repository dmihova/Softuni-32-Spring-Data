package com.softuni.M01SDIntroLab;

import com.softuni.M01SDIntroLab.models.User;
import com.softuni.M01SDIntroLab.services.AccountService;
import com.softuni.M01SDIntroLab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
     //   userService.register("test1",20);
      //  userService.register("test2",21);

       User user = this.userService.findByUsername("test1");
       this.accountService.depositMoney(BigDecimal.TEN, user.getAccountIds().get(0));
       this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccountIds().get(0));


    }
}

