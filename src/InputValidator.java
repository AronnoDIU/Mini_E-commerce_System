import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputValidator {
    private static final String RULES_FILE = "validation_rules.txt";

    private static List<String> validationRules;

    static {
        loadValidationRules();
    }

    private static void loadValidationRules() {
        try {
            validationRules = Files.readAllLines(Path.of(RULES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
        }
    }

    public static boolean isValidString(String input) {
        // Add custom validation rules from the loaded file
        // For simplicity, let's assume the rules are one per line in the file
        for (String rule : validationRules) {
            if (!input.matches(rule)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNumber(String input) {
        return input.matches("\\d+");
    }

    public static boolean isValidEmail(String input) {
        return input.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    public static void addValidationRule(String rule) {
        validationRules.add(rule);
        saveValidationRules();
    }

    private static void saveValidationRules() {
        try {
            Files.write(Path.of(RULES_FILE), validationRules);
        } catch (IOException e) {
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
        }
    }
}