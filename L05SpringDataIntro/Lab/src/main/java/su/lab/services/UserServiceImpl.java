package su.lab.services;

import org.springframework.stereotype.Service;
import su.lab.models.Account;
import su.lab.models.User;
import su.lab.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {
        // Validate username and age
        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed");
        }

        // check username is unique
        Optional<User> oldUser = this.userRepository.findByUsername(username);
        if (oldUser.isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        //create default account
        Account account = new Account();

        // Save user
        User newUser = new User(username,age,account);
        this.userRepository.save(newUser);

    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).get();
    }
}
