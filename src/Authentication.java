import java.util.Map;

// A generic class. This way, it can work with different user types without duplicating code.
public class Authentication<T extends User> {
    private final FileHandler fileHandler;
    private final Map<String, String> userCredentials;
    private String loggedInUser;

    public Authentication(FileHandler fileHandler) {
        this.fileHandler = fileHandler;

        this.userCredentials = fileHandler.readUserCredentials();
        this.loggedInUser = null;
    }

    public User register(String username, String password) {
        if (userCredentials.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
        } else {
            userCredentials.put(username, password);
            fileHandler.writeUserCredentials(userCredentials.toString());
            System.out.println("Registration successful. Welcome, " + username + "!");
        }
        return new User(username, password, username);
    }

    /
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

    // User Logout
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("Logout successful. Goodbye, " + loggedInUser + "!");
            loggedInUser = null;
        } else {
            System.out.println("No user logged in.");
        }
    }
}
