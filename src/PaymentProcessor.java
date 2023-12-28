import java.util.Scanner;

public class PaymentProcessor {
    // Methods for handling payment processing
    public static boolean processPayment(double totalAmount, Scanner scanner) {
        System.out.println("Payment Processing...");
        System.out.println("Total Amount: $" + totalAmount);

        try {
            System.out.println("Enter credit card number:");
            String creditCardNumber = scanner.nextLine();

            System.out.println("Enter expiration date (MM/YYYY):");
            String expirationDate = scanner.nextLine();

            System.out.println("Enter CVV:");
            String cvv = scanner.nextLine();

            // Validate payment details
            validatePaymentDetails(creditCardNumber, expirationDate, cvv);

            // Simulate success or failure
            boolean paymentSuccess = Math.random() < 0.9; // 90% success rate

            if (paymentSuccess) {
                System.out.println("Payment successful! Thank you for your purchase.");
            } else {
                System.out.println("Payment failed. Please check your payment details and try again.");
            }

            return paymentSuccess;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid payment details.");
            return false;
        }
    }

    private static void validatePaymentDetails(String creditCardNumber, String expirationDate, String cvv) {
        // Validate credit card number
        if (creditCardNumber.length() != 16) {
            throw new IllegalArgumentException("Credit card number must be 16 digits.");
        }

        // Validate expiration date
        String[] dateParts = expirationDate.split("/");
        if (dateParts.length != 2) {
            throw new IllegalArgumentException("Expiration date must be in the format MM/YYYY.");
        }

        int month = Integer.parseInt(dateParts[0]);
        int year = Integer.parseInt(dateParts[1]);

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month in expiration date.");
        }

        if (year < 2021) {
            throw new IllegalArgumentException("Invalid year in expiration date.");
        }

        // Validate CVV
        if (cvv.length() != 3) {
            throw new IllegalArgumentException("CVV must be 3 digits.");
        }
    }
}
