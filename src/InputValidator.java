import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class InputValidator {
    private static final Logger logger = Logger.getLogger(InputValidator.class.getName());
    private static final String RULES_FILE = "validation_rules.txt";

    private static List<String> validationRules;

    static {
        loadValidationRules();
    }

    private static void loadValidationRules() {
        try {
            validationRules = Files.readAllLines(Path.of(RULES_FILE));
        } catch (IOException e) {
            logger.severe("Error reading validation rules from file: " + e.getMessage());
        }
    }

    public static boolean isValidString(String input) {
        loadValidationRules();

        // Check if the input matches any of the validation rules
        for (String rule : validationRules) {
            if (input.matches(rule)) {
                // Input matches a validation rule
                return true;
            }
        }
        // Input does not match any validation rule
        return false;
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
            logger.severe("Error writing validation rules to file: " + e.getMessage());
        }
    }
}