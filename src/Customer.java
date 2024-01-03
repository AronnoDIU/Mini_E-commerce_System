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

    public List<Product> viewCart() {
        cart.forEach(System.out::println);
        return null;
    }

    // Method to view order history
    public List<Order> viewOrderHistory() {
        return orderHistory;
    }

    // Getter for cart
    public ShoppingCart getShoppingCart() {
        if (this.shoppingCart == null) {
            this.shoppingCart = new ShoppingCart();
        }
        return this.shoppingCart;
    }


    // Getter for order history
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cart=" + cart +
                ", orderHistory=" + orderHistory +
                '}';
    }
}
