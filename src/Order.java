import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private String orderDetails;
    private OrderStatus orderStatus; // enum type
    private Date orderDate;

    // Constructor
    public Order(Integer orderId, Integer customerId, List<Product> orderDetails,
                 OrderStatus orderStatus, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails.toString();
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(Integer orderId, String username, String products) {
        this.orderId = orderId;
        this.customerId = Integer.valueOf(username);
        this.orderDetails = products;
    }

    public Order(List<Product> products, Admin admin) {
        this.orderDetails = products.toString();
        this.orderStatus = OrderStatus.PLACED;
        this.orderDate = new Date();
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

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Product> orderDetails) {
        this.orderDetails = orderDetails.toString();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId='" + customerId + '\'' +
                ", orderDetails=" + orderDetails +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                '}';
    }
}