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
            System.out.println("Product not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error removing product: " + e.getMessage());
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

    public void viewCart() {
        List<Product> cartItems = shoppingCart.displayCart();

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Shopping Cart Contents:");
            for (Product product : cartItems) {
                System.out.println(product); // Assuming you have a proper toString method in Product class
            }
        }
    }

    public void viewOrderHistory() {
        List<Order> orders = orderManager.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println(order); // Assuming you have a proper toString method in Order class
            }
        }
    }

        public void updateOrderStatus (Order order, OrderStatus newStatus){
            order.setStatus(newStatus);
            LOGGER.log(Level.INFO, "Order {0} status updated to {1} by admin {2}.",
                    new Object[]{order.getOrderId(), newStatus, getUsername()});
        }
    }