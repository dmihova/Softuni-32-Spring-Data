package su.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import su.lab.models.User;
import su.lab.services.AccountService;
import su.lab.services.UserService;

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
/*
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Working");
        this.userService.register("test1",25);
*/

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = this.userService.findByUsername("test1");
        this.accountService
                .depositMoney(BigDecimal.TEN, user.getAccountIds().get(0));
        this.accountService
                .withdrawMoney(BigDecimal.ONE, user.getAccountIds().get(0));
    }

}

