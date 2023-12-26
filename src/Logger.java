import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Logger {
    private static final Logger logger = Logger.getLogger(Logger.class.getName());
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
            logger.severe("Error writing log message to file: " + e.getMessage());
        }
    }

    public static List<String> readLogs() {
        try {
            return Files.readAllLines(Path.of(LOG_FILE));
        } catch (IOException e) {
            logger.severe("Error reading logs from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Logger getLogger(String name) {
        return new Logger();
    }

    public void severe(String s) {
        log("SEVERE: " + s);
    }
}
