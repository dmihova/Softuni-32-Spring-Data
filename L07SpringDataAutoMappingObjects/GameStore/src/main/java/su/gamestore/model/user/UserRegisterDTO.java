package su.gamestore.model.user;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import su.gamestore.constants.OutputMessages;
import su.gamestore.utils.PasswordValidator;
import su.gamestore.utils.Patterns;
public class UserRegisterDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    //  ivan@ivan.com|Ivan12|Ivan12|Ivan

    public UserRegisterDTO() {
    }


    public UserRegisterDTO(String[] params) {
        this.email = params[1];
        this.password = params[2];
        this.confirmPassword = params[3];
        this.fullName = params[4];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> validate() {
       if (fullName.isBlank() || fullName.isEmpty()) {
            return List.of(OutputMessages.USER_NAME_EMPTY);
        }

        if (email.isBlank() || email.isEmpty()) {
            return List.of(OutputMessages.USER_EMAIL_EMPTY);
        }

        if (!password.equals(confirmPassword)) {
            return List.of(OutputMessages.PASSWORDS_DONT_MACH);

        }

        PasswordValidator passwordValidator = new PasswordValidator();
        if (!passwordValidator.isValid(password)) {
            return passwordValidator.getInvalidParameters();
        }


        Pattern emailPattern = Patterns.EMAIL_REGEX;
        Matcher passwordMatcher = emailPattern.matcher(email);
        if (!passwordMatcher.matches()) {
            return List.of(OutputMessages.USER_INVALID_EMAIL);
        }
        return new ArrayList<>();
    }
}







