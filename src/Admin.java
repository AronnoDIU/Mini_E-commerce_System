import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends User {
    private static final Logger LOGGER = Logger.getLogger(Admin.class.getName());
    private final ProductCatalog productCatalog;
    private final OrderManager orderManager;
    private final ShoppingCart shoppingCart;

    // Const
    public Admin(String username, String password, String name,
                 ProductCatalog productCatalog, OrderManager orderManager) {
        super(username, password, name);
        this.productCatalog = productCatalog;
        this.orderManager = orderManager;
        this.shoppingCart = new ShoppingCart();
    }


    // Update an existing product
    public void updateProduct(Product product) {
        productCatalog.updateProduct(product);
        logSuccess();
    }

    private void logSuccess() {
        System.out.println("Product updated" + " successfully!");
    }

    public void placeOrder(List<Product> products) {
        shoppingCart.clearCart();
        products.forEach(shoppingCart::addItem);
        Order newOrder = new Order(shoppingCart.displayCart(), this);
        orderManager.createOrder(newOrder);
        System.out.println("Order placed successfully!");
    }

    public List<Product> viewCart() {
        List<Product> cartItems = shoppingCart.displayCart();

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Shopping Cart Contents:");
            for (Product product : cartItems) {
                System.out.println(product); // Assuming you have a proper toString method in Product class
            }
        }
        return cartItems;
    }

    public List<Order> viewOrderHistory() {
        List<Order> orders = orderManager.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println(order); // Assuming you have a proper toString method in Order class
            }
        }
        return orders;
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        LOGGER.log(Level.INFO, "Order {0} status updated to {1} by admin {2}.",
                new Object[]{order.getOrderId(), newStatus, getUsername()});
    }
}
