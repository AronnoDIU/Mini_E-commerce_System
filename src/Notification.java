import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    private static final String NOTIFICATION_FILE = "notifications.txt";

    public static void sendNotification(String message) {
        String formattedNotification = getFormattedNotification(message);
        System.out.println(formattedNotification); // For demonstration, you might want to use a proper notification system in a real project
        saveNotification(formattedNotification);
    }

    private static String getFormattedNotification(String message) {
        return "[" + System.currentTimeMillis() + "] " + message;
    }

    private static void saveNotification(String formattedNotification) {
        try {
            Files.write(Path.of(NOTIFICATION_FILE), List.of(formattedNotification), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
        }
    }

    public static List<String> readNotifications() {
        try {
            return Files.readAllLines(Path.of(NOTIFICATION_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
            return new ArrayList<>();
        }
    }
}
