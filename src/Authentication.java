import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private final FileHandler fileHandler;
    private final Map<String, String> userCredentials;
    private String loggedInUser;

    // Constructor
    @SuppressWarnings("unchecked")
    public Authentication(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
        this.userCredentials = (Map<String, String>) fileHandler.readUserCredentials(); // Load existing credentials from file
        this.loggedInUser = null;
    }

    // Register a new user
    public User register(String username, String password) {
        if (userCredentials.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
        } else {
//            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            userCredentials.put(username, password);
            fileHandler.writeUserCredentials(userCredentials.toString()); // Save updated credentials to file
            System.out.println("Registration successful. Welcome, " + username + "!");
        }
        return new User(username, password, username);
    }

    // Login
    public User login(String username, String password) {
        if (userCredentials.containsKey(username)) {
//            String hashedPassword = userCredentials.get(username);
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