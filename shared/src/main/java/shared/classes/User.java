package shared.classes;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String authToken;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.authToken = "";
    }

    public User(String username, String password, String authToken) {
        this.username = username;
        this.password = password;
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
