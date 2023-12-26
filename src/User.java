import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static final String USER_FILE = "users.txt";

    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User parseUser(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new User(parts[0], parts[1], parts[2]);
        }
        return null;
    }

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Methods for File Operations

    public static void writeUser(User user) {
        try {
            String userData = user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "\n";
            Files.write(Path.of(USER_FILE), userData.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
        }
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(USER_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    User user = new User(parts[0], parts[1], parts[2]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
        }
        return users;
    }
}
