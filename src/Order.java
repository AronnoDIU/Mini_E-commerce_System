import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;
    private String customerId;
    private List<Product> orderDetails;
    private OrderStatus orderStatus; // enum type
    private Date orderDate;

    // Constructor
    public Order(Integer orderId, String customerId, List<Product> orderDetails,
                 OrderStatus orderStatus, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
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