package su.gamestore.service;

import su.gamestore.model.user.LoginDTO;
import su.gamestore.model.user.UserRegisterDTO;
import su.gamestore.model.user.User;

public interface UserService {
    String register(UserRegisterDTO userRegisterDto);
    String login( LoginDTO loginDTO );
    String logout();

    User getLoggedUser();

    String addItem(String title);

    String removeItem(String title);

    String buyItem();

    String ownedGames();
}
