import java.util.List;

public interface IUserActions {
    // Method to view user profile
    void viewProfile();

    // Method to update user profile
    void updateProfile();

    // Method to place an order with a list of products
    void placeOrder(List<Product> products);

    // Method to view the user's shopping cart
    void viewCart();

    // Method to view the user's order history
    List<Order> viewOrderHistory();
}