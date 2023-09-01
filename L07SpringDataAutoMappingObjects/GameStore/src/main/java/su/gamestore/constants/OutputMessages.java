package su.gamestore.constants;

public class OutputMessages {

    public static final String ENTER_COMMAND = "Please enter a command. End for exit";
    public static final String NO_SUCH_MENU = "No such command";
    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null!";
    public static final String PASSWORD_TOO_SHORT = "Password too short!";
    public static final String PASSWORD_TOO_LONG = "Password too long!";
    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER = "Password should contain lowercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER = "Password should contain uppercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain digit!";
    public static final String PASSWORDS_DONT_MACH = "Passwords don`t mach";
    public static final String USER_NAME_EMPTY = "Username is required";
    public static final String USER_EMAIL_EMPTY = "Username is required";
    public static final String USER_INVALID_EMAIL = "Incorrect email.";

    public static final String USER_INCORRECT_EMAIl_PASSWORD = "Incorrect email / password";

    public static final String USER_SUCCESSFUL_LOGIN = "Successfully logged %s";
    public static final String USER_REGISTER_SUCCESS = "%s was registered";
    public static final String USER_EXIST = "User already exist";
    public static final String USER_EMAIL_EXIST = "Email already registered";
    public static final String USER_SUCCESSFUL_LOGOUT = "User %s successfully logged out.";
    public static final String USER_NO_LOGGED_USER = "No user is logged in.";
    public static final String USER_NO_LOGOUT = " Cannot log out. No user was logged in.";
    public static final String USER_ALREADY_LOGGED_USER = "There is a user logged into the system. Please first log out.";
    public static final String USER_NOT_ADMIN = "The logged user is not admin";


    public static final String GAME_TITLE_TOO_LONG = "Title too long.";
    public static final String GAME_TITLE_TOO_SHORT = "Title too short";
    public static final String GAME_TITLE_MUST_START_WITH_UPPERCASE = "Title must start with uppercase";
    public static final String GAME_DESCRIPTION_TOO_SHORT = "Description too short!";
    public static final String GAME_EXIST = "Game with this title already exist";
    public static final String GAME_SUCCESSFULLY_ADDED = "Added %s";
    public static final String GAME_INVALID_YOUTUBE_ID =
            "YouTube ID must be long 11 characters and consists only upper and lower case alphabets and numeric values";
    public static final String GAME_WRONG_THUMBNAIL_URL = "Wrong thumbnail image URL";
    public static final String GAME_PRICE_NOT_VALID = "The price must be greater than zero";
    public static final String GAME_SIZE_NOT_VALID = "The size of game must be greater than zero";
    public static final String GAME_WRONG_ID = "Game with id %d doesn't exist";
    public static final String GAME_UPDATED = "Game with id %d updated";
    public static final String GAME_DELETED = "Game %s deleted";
    public static final String GAME_NO_SUCH_FIELD = "Incorrect game attribute";
    public static final String GAME_WITH_TITLE_NOT_EXIST ="Incorrect title";
    public static final String       GAME_ALREADY_PURCHASED ="Game already purchased";

    public static final String ORDER_GAME_NOT_IN_CART ="Game not in cart";
    public static final String ORDER_REMOVE_ITEM ="Item removed %s";

    public static final String ORDER_GAME_ADDED ="Game %s  added in cart";
    public static final String ORDER_EMPTY ="Cart is empty";

    public static final String ORDER_COMPLETED = "Successfully bought games: %n%s";
}
