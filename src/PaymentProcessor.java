import java.util.Scanner;

public class PaymentProcessor {
    // Methods for handling payment processing
    public static boolean processPayment(double totalAmount, Scanner scanner) {
        System.out.println("Payment Processing...");
        System.out.println("Total Amount: $" + totalAmount);

        // Simulate payment processing by taking user input
        System.out.println("Enter credit card number:");
        String creditCardNumber = scanner.nextLine();

        System.out.println("Enter expiration date (MM/YYYY):");
        String expirationDate = scanner.nextLine();

        System.out.println("Enter CVV:");
        String cvv = scanner.nextLine();

        // Validate payment details (for simplicity, validation is not implemented here)
        // In a real-world scenario; you would validate credit card information securely

        // Simulate success or failure
        boolean paymentSuccess = Math.random() < 0.9; // 90% success rate

        if (paymentSuccess) {
            System.out.println("Payment successful! Thank you for your purchase.");
        } else {
            System.out.println("Payment failed. Please check your payment details and try again.");
        }

        return paymentSuccess;
    }
}
