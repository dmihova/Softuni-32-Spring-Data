package su.gamestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.gamestore.constants.OutputMessages;
import su.gamestore.model.game.GameAddUpdateDTO;
import su.gamestore.model.user.LoginDTO;
import su.gamestore.model.user.UserRegisterDTO;
import su.gamestore.model.user.User;
import su.gamestore.service.ExecutorService;
import su.gamestore.service.GameService;
import su.gamestore.service.UserService;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    public ExecutorServiceImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public String executeCommand(String commmandline) {
        String[] commandParts = commmandline.split("\\|");
        String commandName = commandParts[0];
        String commandOutput = "";
        switch (commandName) {
            case REGISTER_USER_COMMAND:
                UserRegisterDTO userRegisterDto = new UserRegisterDTO(commandParts);
                commandOutput = userService.register(userRegisterDto);
                break;
            case LOGIN_USER_COMMAND:
                LoginDTO loginDTO = new LoginDTO(commandParts);
                commandOutput = userService.login(loginDTO);
                break;
            case LOGOUT_USER_COMMAND:
                commandOutput = userService.logout();
                break;
            case ADD_GAME:
                commandOutput = check_user_can_modify_game();
                if (!commandOutput.isEmpty()) {
                    break;
                }

                GameAddUpdateDTO gameAddUpdateDTO = new GameAddUpdateDTO(commandParts);
                commandOutput = gameService.addGame(gameAddUpdateDTO);

                break;
            case EDIT_GAME:
                commandOutput = check_user_can_modify_game();
                if (!commandOutput.isEmpty()) {
                    break;
                }
                commandOutput = gameService.editGame(commandParts);

                break;
            case DELETE_GAME:
                commandOutput = check_user_can_modify_game();
                if (!commandOutput.isEmpty()) {
                    break;
                }
                commandOutput= gameService.deleteGame(Long.parseLong(commandParts[1]));
                 break;
            case ALL_GAMES:
                commandOutput= gameService.allGames();
                break;

            case GAME_DETAILS :
                commandOutput=gameService.detailsGame(commandParts[1]);
                break;
            case OWNED_GAMES :
                commandOutput=userService.ownedGames();
                break;
            case ADD_ITEM :
                commandOutput=userService.addItem(commandParts[1]);
                break;
            case REMOVE_ITEM :
                commandOutput=userService.removeItem(commandParts[1]);
                break;
            case BUE_ITEM :
                commandOutput=userService.buyItem();
                break;
            case END :
                commandOutput=END;
                break;
            default:
                commandOutput = OutputMessages.NO_SUCH_MENU;

        }
        return commandOutput;
    }

    @Override
    public User getLoggedUser() {
        return this.userService.getLoggedUser();
    }

    private String check_user_can_modify_game() {

/*        User user = getLoggedUser();
        if(user == null) {
            return OutputMessages.USER_NO_LOGGED_USER;
        }
        if(!user.isAdmin()) {
            return OutputMessages.USER_NOT_ADMIN;
        }*/
        return "";
    }
}


