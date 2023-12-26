import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ReportGenerator {
    private static final String REPORT_FILE = "report.txt";

    public static void generateReport() {
        try {
            List<Order> orders = Order.readOrders();
            List<Payment> payments = Payment.readPayments();
            List<Product> products = Product.readProducts();

            StringBuilder reportContent = new StringBuilder();
            reportContent.append("=== Orders ===\n");
            for (Order order : orders) {
                reportContent.append("Customer: ").append(order.getCustomerName()).append("\n");
                reportContent.append("Items: ").append(String.join(", ", order.getItems())).append("\n");
                reportContent.append("\n");
            }

            reportContent.append("=== Payments ===\n");
            for (Payment payment : payments) {
                reportContent.append("Customer: ").append(payment.getCustomerName()).append("\n");
                reportContent.append("Amount: ").append(payment.getAmount()).append("\n");
                reportContent.append("Payment Date: ").append(payment.getPaymentDate()).append("\n");
                reportContent.append("\n");
            }

            reportContent.append("=== Products ===\n");
            for (Product product : products) {
                reportContent.append("Name: ").append(product.getName()).append("\n");
                reportContent.append("Price: ").append(product.getPrice()).append("\n");
                reportContent.append("Quantity: ").append(product.getQuantity()).append("\n");
                reportContent.append("\n");
            }

            Files.write(Path.of(REPORT_FILE), reportContent.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
        }
    }

    public static String readReport() {
        try {
            return Files.readString(Path.of(REPORT_FILE));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
            return "";
        }
    }
}
