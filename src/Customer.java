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
    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public void viewCart() {
        cart.forEach(System.out::println);
    }

    // Method to place an order
    public void placeOrder() {
        if (!cart.isEmpty()) {
            Order newOrder = new Order(cart, this);
            orderHistory.add(newOrder);
            cart.clear();
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Cart is empty. Cannot place an empty order.");
        }
    }

    // Method to view order history
    public List<Order> viewOrderHistory() {
        return orderHistory;
    }

    // Getter for cart
    public List<Product> getCart() {
        return cart;
    }

    // Getter for order history
    public List<Order> getOrderHistory() {
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
