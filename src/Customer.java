import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private final List<Product> cart;
    private final List<Order> orderHistory;

    public Customer(String userId, String username, String password, String address, String email) {
        super(userId, username, password, address, email);
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    // Methods for cart management
    public void addToCart(Product item) {
        cart.add(item);
    }

    public void removeFromCart(Product item) {
        cart.remove(item);
    }

    public void viewCart() {
        cart.forEach(System.out::println);
    }

    // Method to place an order
//    public void placeOrder() {
//        // Create a new order
//        Order newOrder = new Order(cart);
//
//        // Add the order to the order manager
//        OrderManager orderManager = new OrderManager();
//        orderManager.createOrder(newOrder);
//
//        // Add the order to order history
//        orderHistory.add(newOrder);
//
//        // Clear the cart after placing the order
//        cart.clear();
//    }

    // Method to view order history
    public List<Order> viewOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + getUserId() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", cart=" + cart +
                ", orderHistory=" + orderHistory +
                '}';
    }
}