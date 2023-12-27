import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderManager {
    private final List<Order> orders;

    // Constructor
    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    // Create a new order
    public void createOrder(Order order) {
        orders.add(order);
        System.out.println("Order created successfully. Order ID: " + order.getOrderId());
    }

    // Cancel an existing order
    public void cancelOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order != null) {
            orders.remove(order);
            System.out.println("Order canceled successfully. Order ID: " + orderId);
        } else {
            System.out.println("Order not found. Unable to cancel.");
        }
    }

    // Update the status of an existing order
    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = findOrder(orderId);
        if (order != null) {
            order.setOrderStatus(newStatus);
            System.out.println("Order status updated successfully. Order ID: " + orderId);
        } else {
            System.out.println("Order not found. Unable to update status.");
        }
    }

    // Find an order by its ID
    private Order findOrder(String orderId) {
        for (Order order : orders) {
            if (Objects.equals(order.getOrderId(), orderId)) {
                return order;
            }
        }
        return null;
    }

    // Get a copy of all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}