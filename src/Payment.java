import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private static final String PAYMENTS_FILE = "payments.txt";

    private String customerName;
    private double amount;
    private String paymentDate;

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
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
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
            e.printStackTrace();
            // You might want to handle this more gracefully in a production scenario
            return new ArrayList<>();
        }
    }
}