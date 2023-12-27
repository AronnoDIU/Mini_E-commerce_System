import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private final FileHandler fileHandler;
    private final Map<String, String> userCredentials;
    private String loggedInUser;

    // Constructor
    public Authentication(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.userCredentials = new HashMap<>();
        this.loggedInUser = null;
    }

    // Register a new user
    public User register(String username, String password) {
        if (userCredentials.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
        } else {
            userCredentials.put(username, password);
            fileHandler.writeFile(userCredentials.toString());
            System.out.println("Registration successful. Welcome, " + username + "!");
        }
        return new User(username, password, username);
    }

    // Login
    public User login(String username, String password) {
        if (userCredentials.containsKey(username)) {
            if (userCredentials.get(username).equals(password)) {
                loggedInUser = username;
                System.out.println("Login successful. Welcome, " + username + "!");
            } else {
                System.out.println("Incorrect password. Please try again.");
            }
        } else {
            System.out.println("Username not found. Please try again.");
        }
        return loggedInUser != null ? new User(loggedInUser) : null;
    }

    // Logout
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logout successful. Goodbye, " + loggedInUser + "!");
            loggedInUser = null;
        } else {
            System.out.println("No user logged in.");
        }
    }
}