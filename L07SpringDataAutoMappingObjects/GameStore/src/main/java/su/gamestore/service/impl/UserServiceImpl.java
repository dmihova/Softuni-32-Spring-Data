package su.gamestore.service.impl;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import su.gamestore.model.game.Game;
import su.gamestore.model.order.Order;
import su.gamestore.model.user.LoginDTO;
import su.gamestore.model.user.UserRegisterDTO;
import su.gamestore.model.user.User;
import su.gamestore.repository.GameRepository;
import su.gamestore.repository.OrderRepository;
import su.gamestore.repository.UserRepository;
import su.gamestore.service.UserService;
import su.gamestore.constants.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;

    private User currentUser;

    private Set<String> cart;

    private Set<String> ownedGames;

    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository, OrderRepository orderRepository) {
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.currentUser =null;
        this.userRepository = userRepository;
    }

    @Override
    public String register(UserRegisterDTO userRegisterDto) {
        if(this.currentUser!=null){
            return OutputMessages.USER_ALREADY_LOGGED_USER;
        }

        List<String> invalidDataMessage = userRegisterDto.validate();
        if (!invalidDataMessage.isEmpty()) {
                return invalidDataMessage
                        .stream().collect(Collectors.joining(System.lineSeparator()));
        }
        int countByName = this.userRepository.countByFullName(userRegisterDto.getFullName());
        if (countByName>0){
            return OutputMessages.USER_EXIST;
        }

        int countByEmail = this.userRepository.countByEmail(userRegisterDto.getEmail());
        if (countByEmail>0){
            return OutputMessages.USER_EMAIL_EXIST;
        }

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userRegisterDto,User.class);
        long count = this.userRepository.count();
        if (count==0){
            user.setAdmin(true);
        }
        userRepository.save(user);
        return String.format(OutputMessages.USER_REGISTER_SUCCESS, userRegisterDto.getFullName());
    }

    @Override
    public String login( LoginDTO loginDTO) {
        if(this.currentUser!=null){
            return OutputMessages.USER_ALREADY_LOGGED_USER;
        }

        Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if (user.isPresent()) {
            this.currentUser=user.get();
            this.cart = new HashSet<>();
            this.ownedGames = getOwnedGames();

            return String.format(OutputMessages.USER_SUCCESSFUL_LOGIN, user.get().getFullName());

        }else{
            return OutputMessages.USER_INCORRECT_EMAIl_PASSWORD;
        }
    }
    private Set<String> getOwnedGames() {
        return this.currentUser
                .getGames().stream()
                .map(Game::getTitle)
                .collect(Collectors.toSet());
    }
    @Override
    public String logout() {
        if(this.currentUser==null){
            return OutputMessages.USER_NO_LOGOUT;
        }
        this.currentUser =null;
        return OutputMessages.USER_SUCCESSFUL_LOGOUT;

    }

    public User getLoggedUser() {
        return currentUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.currentUser = loggedUser;
    }



    @Override
    public String ownedGames() {
        if(this.currentUser==null){
            return OutputMessages.USER_NO_LOGGED_USER;
        }

        return String.join(System.lineSeparator(), ownedGames);
    }

    @Override
    public String removeItem(String title) {
        if(this.currentUser==null){
            return OutputMessages.USER_NO_LOGGED_USER;
        }
        if(!this.cart.remove(title)) {
            return OutputMessages.ORDER_GAME_NOT_IN_CART;
        }

        return String.format(OutputMessages.ORDER_REMOVE_ITEM, title);
    }

    @Override
    public String addItem(String title) {
        if(this.currentUser==null){
            return OutputMessages.USER_NO_LOGGED_USER;
        }
        int gameInStore = gameRepository.countByTitleAndDeletedFalse(title);

        if(gameInStore <=0 ) {
            return OutputMessages.GAME_WITH_TITLE_NOT_EXIST;
        }

        if(this.ownedGames.contains(title)){
            return OutputMessages.GAME_ALREADY_PURCHASED;
        }

        this.cart.add(title);

        return String.format(OutputMessages.ORDER_GAME_ADDED, title);
    }
    @Override
    public String buyItem() {
        if(this.currentUser==null){
            return OutputMessages.USER_NO_LOGGED_USER;
        }
        if(this.cart.isEmpty()) {
            return OutputMessages.ORDER_EMPTY ;
        }

        Set<Game> gameToBuy = gameRepository.findByTitleIn(this.cart);

        this.currentUser.getGames().addAll(gameToBuy);
        userRepository.save(this.currentUser);
        this.ownedGames = getOwnedGames();

        Order order = new Order(this.currentUser, gameToBuy);

        orderRepository.save(order);

        this.cart.clear();

        return String.format(OutputMessages.ORDER_COMPLETED,
                gameToBuy.stream()
                        .map(g -> "\t-" + g.getTitle())
                        .collect(Collectors.joining(System.lineSeparator())));
    }
}


