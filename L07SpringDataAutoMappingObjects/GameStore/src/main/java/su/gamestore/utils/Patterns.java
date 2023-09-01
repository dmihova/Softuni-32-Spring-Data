package su.gamestore.utils;
import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Za-z0-9]+([-_.]?[A-Za-z0-9]+)*@([A-Za-z]+(-[A-Za-z]+)*)([.]([A-Za-z]+(-[A-Za-z]+)*))+$");
    public static final Pattern GAME_YOUTUBE_ID_REGEX = Pattern.compile(
            "^(?=[a-zA-z0-9]*[A-Z])(?=[a-zA-z0-9]*[0-9])(?=[a-zA-z0-9]*[a-z])[a-zA-z0-9]{11}$");
    public static final Pattern GAME_TITLE_REGEX = Pattern.compile("^[A-Z].*$");;
    public static String DATE_FORMAT = "dd-MM-yyyy";;
}
