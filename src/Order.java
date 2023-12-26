import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Order {
    private static final Logger logger = Logger.getLogger(Order.class.getName());
    private static final String ORDERS_FILE = "orders.txt";

    private final String customerName;
    private final List<String> items;

    public Order(String customerName, List<String> items) {
        this.customerName = customerName;
        this.items = items;
        saveOrder();
    }

    public static Order loadOrder(String orderDetails) {
        String[] lines = orderDetails.split("\n");
        String customerName = lines[0].substring("Customer: ".length());
        String itemsLine = lines[1].substring("Items: ".length());
        String[] itemsArray = itemsLine.split(", ");
        List<String> items = new ArrayList<>(Arrays.asList(itemsArray));
        return new Order(customerName, items);
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getItems() {
        return items;
    }

    private void saveOrder() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Customer: " + customerName);
            lines.add("Items: " + String.join(", ", items));
            lines.add(""); // Add a blank line to separate orders
            Files.write(Path.of(ORDERS_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing order to file: " + e.getMessage());
        }
    }

    public static List<Order> readOrders() {
        try {
            List<Order> orders = new ArrayList<>();
            List<String> lines = Files.readAllLines(Path.of(ORDERS_FILE));

            String currentCustomerName = null;
            List<String> currentItems = new ArrayList<>();

            for (String line : lines) {
                if (line.startsWith("Customer: ")) {
                    if (currentCustomerName != null) {
                        orders.add(new Order(currentCustomerName, currentItems));
                        currentItems.clear();
                    }
                    currentCustomerName = line.substring("Customer: ".length());
                } else if (line.startsWith("Items: ")) {
                    String itemsLine = line.substring("Items: ".length());
                    String[] itemsArray = itemsLine.split(", ");
                    currentItems.addAll(Arrays.asList(itemsArray));
                }
            }

            if (currentCustomerName != null) {
                orders.add(new Order(currentCustomerName, currentItems));
            }

            return orders;
        } catch (IOException e) {
            logger.severe("Error reading orders from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public String getOrderDetails() {
        return "Customer: " + customerName + "\n" +
                "Items: " + String.join(", ", items);
    }
}
