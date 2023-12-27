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