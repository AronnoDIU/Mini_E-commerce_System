import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private final String orderId;
    private final String username;
    private final List<Product> products;
    private final Date dateTime;
    private final String customerId;
    private final List<Product> orderDetails;
    private final OrderStatus orderStatus;
    private final LocalDateTime orderDate;
    private final LocalDateTime orderTime;

    // Constructor for creating an order with specified parameters
    public Order(String orderId, String username, List<Product> products, Date dateTime,
                 String customerId, List<Product> orderDetails, OrderStatus orderStatus,
                 LocalDateTime orderDate) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
        this.dateTime = dateTime;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderTime = LocalDateTime.now();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String toFileString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Append order details

        return orderId + "," +
                username + "," +
                productsToString() + "," +
                dateFormat.format(dateTime) + "," +
                timeFormat.format(dateTime);
    }

    private String productsToString() {
        // Convert the list of products to a string representation
        StringBuilder productsString = new StringBuilder();
        for (Product product : products) {
            productsString.append(product.getProductId()).append(":").append(product.getName()).append(",");
        }

        // Remove the trailing comma
        if (!productsString.isEmpty()) {
            productsString.setLength(productsString.length() - 1);
        }

        return productsString.toString();
    }

    // Other methods...

    // toString method for easy display
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", username='" + username + '\'' +
                ", products=" + products +
                ", dateTime=" + dateTime +
                ", customerId='" + customerId + '\'' +
                ", orderDetails=" + orderDetails +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                '}';
    }
}
