import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private static final String USER_FILE = "users.txt";
    private static Map<String, String> userCredentials; // Username to password mapping

    public AuthenticationManager() {
        userCredentials = loadUsers();
    }

    public static Map<String, String> getUserCredentials() {
        return userCredentials;
    }

    private Map<String, String> loadUsers() {
        Map<String, String> users = new HashMap<>();
        try {
            Files.lines(Paths.get(USER_FILE)).forEach(line -> {
                String[] parts = line.split(",");
                users.put(parts[0], parts[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void registerUser(String username, String password) {
        if (!userCredentials.containsKey(username)) {
            userCredentials.put(username, password);
            saveUsers();
            System.out.println("User registered successfully: " + username);
        } else {
            System.out.println("Username already exists. Choose another username.");
        }
    }

    public boolean loginUser(String username, String password) {
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    private static void saveUsers() {
        try {
            Path path = Paths.get(USER_FILE);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, () -> userCredentials.entrySet().stream()
                            .map(entry -> entry.getKey() + "," + entry.getValue()).iterator(),
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}