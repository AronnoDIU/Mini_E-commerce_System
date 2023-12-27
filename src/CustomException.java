import java.util.Scanner;

public class CustomException extends Exception {
    // Custom exception handling

    public CustomException(String message) {
        super(message);
    }

    // Other features and methods related to the custom exception
}

//public class ExampleClass {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            // Example usage of the custom exception
//            int userInput = getUserInput(scanner);
//            if (userInput < 0) {
//                throw new CustomException("Input must be a non-negative number.");
//            }
//
//            System.out.println("User input: " + userInput);
//        } catch (CustomException e) {
//            System.out.println("Custom Exception: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
//    }
//
//    // Sample method that may throw the custom exception
//    private static int getUserInput(Scanner scanner) throws CustomException {
//        System.out.println("Enter a non-negative number:");
//        int input = scanner.nextInt();
//
//        // Example condition that triggers the custom exception
//        if (input < 0) {
//            throw new CustomException("Input must be a non-negative number.");
//        }
//
//        return input;
//    }
//}
