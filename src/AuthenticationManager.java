import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class AuthenticationManager {
    private static final Logger logger = Logger.getLogger(AuthenticationManager.class.getName());
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
            Path path = Paths.get(USER_FILE);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            // Use try-with-resources to ensure the stream is closed
            try (var linesStream = Files.lines(path)) {
                linesStream.forEach(line -> {
                    String[] parts = line.split(",");
                    users.put(parts[0], parts[1]);
                });
            }
        } catch (IOException e) {
            logger.severe("Error reading users from file: " + e.getMessage());
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

            // Convert the map entries to lines and write them to the file
            String content = userCredentials.entrySet().stream()
                    .map(entry -> entry.getKey() + "," + entry.getValue())
                    .collect(Collectors.joining(System.lineSeparator()));

            Files.writeString(path, content, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing users to file: " + e.getMessage());
        }
    }
}