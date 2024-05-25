package org.softuni.services;

import org.softuni.models.entities.Town;
import org.softuni.models.entities.User;
import org.softuni.repositories.TownRepository;
import org.softuni.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SeedServiceImpl implements SeedService{

    private static final String FILE_PATH = "M03User/"+"src/main/resources/files/";
    private static final String USERS_FILE_NAME = FILE_PATH + "users.txt";
    private static final String TOWNS_FILE_NAME = FILE_PATH + "towns.txt";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TownRepository townRepository;

    public SeedServiceImpl(UserRepository userRepository, TownRepository townRepository) {
        this.userRepository = userRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void seedTowns() throws IOException {
        Files.readAllLines(Path.of(TOWNS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(";\\s+"))
                .map(town -> new Town(town[0], town[1]))
                .forEach(townRepository::save);
    }



    @Override
    public void seedUsers() throws IOException {
        Files.readAllLines(Path.of(USERS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getUserObject)
                .forEach(userRepository::save);

    }

    private User getUserObject(String line) {
        String[] userParts = line.split(";\\s+");

        String firstName = userParts[0];
        String lastName = userParts[1];
        String username = userParts[2];
        String password = userParts[3];
        String email = userParts[4];
        int age = Integer.parseInt(userParts[5]);
        Town bornTown = townRepository.getByName(userParts[6]);
        Town livingTown = townRepository.getByName(userParts[7]);
        LocalDate lastTimeLongedIn = LocalDate.parse(userParts[8], DateTimeFormatter.ofPattern("d/M/yyyy"));

        return new User(firstName, lastName, username, password, email, age, bornTown, livingTown, lastTimeLongedIn);
    }
}
