import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Customer {
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private static final String CUSTOMER_FILE = "customers.txt";

    private final String name;
    private final String email;
    private final List<Order> orders;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void placeOrder(Order order) {
        orders.add(order);
        saveCustomer();
    }

    private void saveCustomer() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Name:" + name);
            lines.add("Email:" + email);
            lines.add("Orders:");
            for (Order order : orders) {
                lines.add(order.getOrderDetails());
            }
            Files.write(Paths.get(getCustomerFileName()), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing customer to file: " + e.getMessage());
        }
    }

    private String getCustomerFileName() {
        return name.toLowerCase() + ".txt";
    }

    public static Customer loadCustomer(String customerName) {
        Customer customer = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(customerName.toLowerCase() + ".txt"));
            String customerEmail = lines.get(1).substring("Email:".length());
            customer = new Customer(customerName, customerEmail);
            for (int i = 3; i < lines.size(); i++) {
                String orderDetails = lines.get(i);
                Order order = Order.loadOrder(orderDetails);
                customer.placeOrder(order);
            }
        } catch (IOException e) {
            logger.severe("Error reading customer from file: " + e.getMessage());
        }
        return customer;
    }
}