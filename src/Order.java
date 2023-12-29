import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private final String orderId;
    private final String customerId;
    private final List<Product> orderDetails;
    private OrderStatus orderStatus;
    private final LocalDateTime orderDate;

    public Order(String orderId, String customerId, List<Product> orderDetails, OrderStatus orderStatus,
                 LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(String orderId, String customerId, List<Product> orderDetails, OrderStatus orderStatus) {
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

    public Order(Integer orderId, Integer customerId, List<Product> orderDetails, OrderStatus orderStatus) {
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