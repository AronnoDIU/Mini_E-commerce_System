import java.util.List;
import java.util.logging.Logger;

public class Admin extends User {
    private static final Logger LOGGER = Logger.getLogger(Admin.class.getName());
    private final ProductCatalog productCatalog;
    private final OrderManager orderManager;
    private final ShoppingCart shoppingCart;

    // Constructor
    public Admin(String username, String password, String name,
                 ProductCatalog productCatalog, OrderManager orderManager) {
        super(username, password, name);
        this.productCatalog = productCatalog;
        this.orderManager = orderManager;
        this.shoppingCart = new ShoppingCart();
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public OrderManager getOrderManager() {
        return orderManager;
    }

    // Add a product to the catalog
    public void addProduct(Product product) {
        // Implement logic to add a new product, e.g., calling a method in the ProductHandler class
        ProductHandler.addProduct(product);
        LOGGER.log(Level.INFO, "New product added by admin {0}: {1}.",
                new Object[]{getUsername(), product.getName()});
    }

    // Remove a product from the catalog
    public void removeProduct(Integer productId) {
        try {
            productCatalog.removeProduct(productId);
            System.out.println("Product removed successfully!");
        } catch (ProductNotFoundException e) {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    // Update an existing product
    public void updateProduct(Product product) {
        productCatalog.updateProduct(product);
        logSuccess("Product updated");
    }

    private void logSuccess(String action) {
        System.out.println(action + " successfully!");
    }

    public void viewProductStats() {
        // View product statistics
        productCatalog.viewProductStatistics();
    }

    public void manageUsers() {
        // Manage users
        System.out.println("Users managed by admin.");
    }

    public void placeOrder(List<Product> products) {
        shoppingCart.clearCart();
        Order newOrder = new Order(products, this, this.getShoppingCart());
        orderManager.createOrder(newOrder);
        System.out.println("Order placed successfully!");
    }

    public List<Product> viewCart() {
        // View cart
        System.out.println("Cart viewed by admin.");
        return shoppingCart.displayCart();
    }

    public List<Order> viewOrderHistory() {
        // View order history
        System.out.println("Order history viewed by admin.");
        return orderManager.getAllOrders();
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        LOGGER.log(Level.INFO, "Order {0} status updated to {1} by admin {2}.",
                new Object[]{order.getOrderId(), newStatus, getUsername()});
    }
}