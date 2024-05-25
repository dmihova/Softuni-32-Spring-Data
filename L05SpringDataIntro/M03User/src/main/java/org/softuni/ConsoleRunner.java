package org.softuni;

import org.softuni.models.entities.Town;
import org.softuni.models.entities.User;
import org.softuni.repositories.TownRepository;
import org.softuni.repositories.UserRepository;
import org.softuni.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final UserRepository userRepository;
    private final TownRepository townRepository;

    public ConsoleRunner(SeedService seedService, UserRepository userRepository, TownRepository townRepository) {
        this.seedService = seedService;
        this.userRepository = userRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // this.seedService.seedAll();
        // findUsersByEmail();
        //   removeInactiveUsers();
        testCreateUser();

    }

    private void testCreateUser() {

        Town town = townRepository.getByName("Sofia");
        LocalDate lastTimeLongedIn = LocalDate.now();

        User u = new User("u9", "ln_u9", "fn_u9",
                "password", "test", 25, town, town, lastTimeLongedIn);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(u);
        int count = constraintViolations.size();
        for (ConstraintViolation<User> violation : constraintViolations) {
            System.out.println(violation.getMessage());
        }
        //userRepository.save(u);
    }

    private void findUsersByEmail() {
        System.out.println("Input email");
        final String email = "@" + new Scanner(System.in).nextLine();
        List<User> users = userRepository.findByEmailEndingWith(email);
        if (users.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        users.stream()
                .map(u -> String.format("%s with username: %s has e-mail: %s",
                        u.getFullName(), u.getUsername(), u.getEmail()))
                .forEach(System.out::println);
    }

    private void removeInactiveUsers() {
        System.out.println("Input date d/m/yyyy");

        String[] datePart = new Scanner(System.in).nextLine().split("/");

        final LocalDate lastLoggedInBefore =
                LocalDate.of(Integer.parseInt(datePart[2]), Integer.parseInt(datePart[1]), Integer.parseInt(datePart[0]));

        List<User> users = userRepository.findByLastTimeLoggedInIsBefore(lastLoggedInBefore);

        users.forEach(u -> {
            u.setDeleted(true);
            userRepository.save(u);
        });

        System.out.printf("Count of users to delete %d", users.size());

        userRepository.deleteAllByIsDeleted(true);

    }
}
