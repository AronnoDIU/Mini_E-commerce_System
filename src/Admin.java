import java.util.List;

public class Admin extends User implements IAdminActions {
    private final ProductCatalog productCatalog;
    private final OrderManager orderManager;
    private final ShoppingCart shoppingCart;

    // Constructor
    public Admin(String username, String password, String name, ProductCatalog productCatalog, OrderManager orderManager) {
        super(username, password, name);
        this.setUsername(username);
        this.setPassword(password);
        this.name = name;
        this.productCatalog = productCatalog;
        this.orderManager = orderManager;
        this.shoppingCart = new ShoppingCart();
    }

    // Add a product to the catalog
    public void addProduct(Product product) {
        productCatalog.addProduct(product);
    }

    // Remove a product from the catalog
    public void removeProduct(Integer productId) {
        productCatalog.removeProduct(productId);
    }

    // Update an existing product
    public void updateProduct(Product product) {
        productCatalog.updateProduct(product);
    }

    // View all orders
    public List<Order> viewOrders() {
        return orderManager.getAllOrders();
    }

    @Override
    public void viewProductStats() {
        // View product statistics
        productCatalog.viewProductStatistics();
    }

    @Override
    public void manageUsers() {
        // Manage users
        System.out.println("Users managed by admin.");
    }

    @Override
    public void placeOrder(List<Product> products) {
        shoppingCart.clearCart();
        orderManager.createOrder((Order) products);
    }

    @Override
    public void viewCart() {
        // View cart
        System.out.println("Cart viewed by admin.");
    }

    @Override
    public List<Order> viewOrderHistory() {
        // View order history
        System.out.println("Order history viewed by admin.");
        return null;
    }
}