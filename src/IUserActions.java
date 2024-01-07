import java.util.List;

/**
 * The {@code IUserActions} interface defines actions that can be performed by a user in the system.
 */
public interface IUserActions {
    /**
     * 
    void viewProfile();

    /**
     * Update the user's profile.
     */
    void updateProfile();

    /**
     * Place an order with a list of products.
     *
     * @param products The list of products to include in the order.
     */
    void placeOrder(List<Product> products);

    /**
     * View the user's shopping cart.
     *
     */
    List<Product> viewCart();

    /**
     * View the user's order history.
     *
     * @return The list of orders in the user's history.
     */
    List<Order> viewOrderHistory();
}
