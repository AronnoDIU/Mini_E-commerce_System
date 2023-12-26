import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Notification {
    private static final Logger logger = Logger.getLogger(Notification.class.getName());
    private static final String NOTIFICATION_FILE = "notifications.txt";

    public static void sendNotification(String message) {
        String formattedNotification = getFormattedNotification(message);
        System.out.println(formattedNotification);
        saveNotification(formattedNotification);
    }

    private static String getFormattedNotification(String message) {
        return "[" + System.currentTimeMillis() + "] " + message;
    }

    private static void saveNotification(String formattedNotification) {
        try {
            Files.write(Path.of(NOTIFICATION_FILE), List.of(formattedNotification), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing notification to file: " + e.getMessage());
        }
    }

    public static List<String> readNotifications() {
        try {
            return Files.readAllLines(Path.of(NOTIFICATION_FILE));
        } catch (IOException e) {
            logger.severe("Error reading notifications from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
