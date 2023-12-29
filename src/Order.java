import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private final String orderId;
    private String username;
    private List<Product> products;
    private Date dateTime;
    private final String customerId;
    private final List<Product> orderDetails;
    private OrderStatus orderStatus;
    private final LocalDateTime orderDate;

    public Order(String orderId, String customerId, List<Product> orderDetails,
                 OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(String orderId, String customerId,
                 List<Product> orderDetails, OrderStatus orderStatus) {
        this(orderId, customerId, orderDetails, orderStatus, LocalDateTime.now());
    }

    public Order(Integer orderId, Integer customerId, List<Product> orderDetails,
                 OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId.toString();
        this.customerId = customerId.toString();
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(Integer orderId, Integer customerId,
                 List<Product> orderDetails, OrderStatus orderStatus) {
        this(orderId, customerId, orderDetails, orderStatus, LocalDateTime.now());
    }

    public Order(List<Product> products, Admin admin) {
        this(null, admin.getUserId(), products, OrderStatus.PENDING);
    }

    public Order(String orderId, String username, List<Product> products, Date dateTime) {
        this.orderId = orderId;
        this.customerId = username;
        this.orderDetails = products;
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.ofInstant(dateTime.toInstant(), FileHandler.DEFAULT_ZONE_ID);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String toFileString() {
        StringBuilder orderString = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        // Append order details
        orderString.append(orderId).append(",");
        orderString.append(username).append(",");
        orderString.append(productsToString()).append(",");
        orderString.append(dateFormat.format(dateTime)).append(",");
        orderString.append(timeFormat.format(dateTime));

        return orderString.toString();
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

    // toString method for easy display
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDetails=" + orderDetails +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                '}';
    }
}