import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final String LOG_FILE = "log.txt";

    public static void log(String message) {
        String formattedMessage = getFormattedLogMessage(message);
        System.out.println(formattedMessage); // For demonstration, you might want to use a logging framework in a real project
        saveLogMessage(formattedMessage);
    }

    private static String getFormattedLogMessage(String message) {
        return "[" + System.currentTimeMillis() + "] " + message;
    }

    private static void saveLogMessage(String formattedMessage) {
        try {
            Files.write(Path.of(LOG_FILE), List.of(formattedMessage), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
        }
    }

    public static List<String> readLogs() {
        try {
            return Files.readAllLines(Path.of(LOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
            return new ArrayList<>();
        }
    }
}
