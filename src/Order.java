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
    private OrderStatus orderStatus;
    private final LocalDateTime orderDate;
    private final LocalDateTime orderTime;

    // Const Creating an order with specified parameters
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

    public Order(List<Product> cartItems, User user) {
        this.orderId = "ORD" + (OrderManager.getOrders().size() + 1);
        this.username = user.getUsername();
        this.products = cartItems;
        this.dateTime = new Date();
        this.customerId = user.getUserId();
        this.orderDetails = cartItems;
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.orderTime = LocalDateTime.now();
    }

    public Order(String orderId, String username, List<Product> products, LocalDateTime dateTime) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
        this.dateTime = new Date();
        this.customerId = "CUST" + (OrderManager.getOrders().size() + 1);
        this.orderDetails = products;
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
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

    public void setOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }
}
