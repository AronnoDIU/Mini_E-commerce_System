import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileManager {
    private static final Logger logger = Logger.getLogger(FileManager.class.getName());
    private static final String DEFAULT_FILE_NAME = "data.txt";

    public static List<String> readFromFile() {
        return readFromFile(DEFAULT_FILE_NAME);
    }

    public static List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            logger.severe("Error reading from file: " + e.getMessage());
            return null;
        }
    }

    public static void writeToFile(List<String> lines) {
        writeToFile(lines, DEFAULT_FILE_NAME);
    }

    public static void writeToFile(List<String> lines, String fileName) {
        try {
            Files.write(Path.of(fileName), lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            logger.severe("Error writing to file: " + e.getMessage());
        }
    }
}