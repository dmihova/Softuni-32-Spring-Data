package su.lab.services;

import su.lab.models.User;

public interface UserService {
    void register(String  username, int age);

    User findByUsername(String first);
}
