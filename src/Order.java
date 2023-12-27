import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private List<Product> orderDetails;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    // Constructors
    public Order(Integer orderId, Integer customerId, List<Product> orderDetails,
                 OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(Integer orderId, User user, List<Product> orderDetails,
                 OrderStatus orderStatus, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = user.getUserId();
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Product> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Product> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        if (orderDate != null) {
            this.orderDate = orderDate;
        }
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDetails=" + orderDetails +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                '}';
    }
}