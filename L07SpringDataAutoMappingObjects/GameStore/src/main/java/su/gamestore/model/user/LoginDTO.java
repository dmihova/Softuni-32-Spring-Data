package su.gamestore.model.user;

public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO(String [] params) {
        this.email = params[1];
        this.password = params[2];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
