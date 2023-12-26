import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Payment {
    private static final Logger logger = Logger.getLogger(Payment.class.getName());
    private static final String PAYMENTS_FILE = "payments.txt";

    private final String customerName;
    private final double amount;
    private final String paymentDate;

    public Payment(String customerName, double amount, String paymentDate) {
        this.customerName = customerName;
        this.amount = amount;
        this.paymentDate = paymentDate;
        savePayment();
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    private void savePayment() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Customer: " + customerName);
            lines.add("Amount: " + amount);
            lines.add("Payment Date: " + paymentDate);
            lines.add(""); // Add a blank line to separate payments
            Files.write(Path.of(PAYMENTS_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing payment to file: " + e.getMessage());
        }
    }

    public static List<Payment> readPayments() {
        try {
            List<Payment> payments = new ArrayList<>();
            List<String> lines = Files.readAllLines(Path.of(PAYMENTS_FILE));

            String currentCustomerName = null;
            double currentAmount = 0;
            String currentPaymentDate = null;

            for (String line : lines) {
                if (line.startsWith("Customer: ")) {
                    if (currentCustomerName != null) {
                        payments.add(new Payment(currentCustomerName, currentAmount, currentPaymentDate));
                    }
                    currentCustomerName = line.substring("Customer: ".length());
                } else if (line.startsWith("Amount: ")) {
                    currentAmount = Double.parseDouble(line.substring("Amount: ".length()));
                } else if (line.startsWith("Payment Date: ")) {
                    currentPaymentDate = line.substring("Payment Date: ".length());
                }
            }

            if (currentCustomerName != null) {
                payments.add(new Payment(currentCustomerName, currentAmount, currentPaymentDate));
            }

            return payments;
        } catch (IOException e) {
            logger.severe("Error reading payments from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}