package su.gamestore.service;

import su.gamestore.model.user.User;

public interface ExecutorService {
    final String REGISTER_USER_COMMAND = "RegisterUser";
    final String LOGIN_USER_COMMAND = "LoginUser";
    final String LOGOUT_USER_COMMAND = "Logout";

    final String ADD_GAME = "AddGame";
    final String EDIT_GAME = "EditGame";
    final String DELETE_GAME = "DeleteGame";
    final String ALL_GAMES = "AllGames";
    final String GAME_DETAILS = "DetailGame";
    final String OWNED_GAMES = "OwnedGames";
    final String ADD_ITEM = "AddItem";
    final String REMOVE_ITEM = "RemoveItem";
    final String BUE_ITEM = "BuyItem";
    final String END = "End";

    String executeCommand(String commmandline);

    User getLoggedUser( );
}
