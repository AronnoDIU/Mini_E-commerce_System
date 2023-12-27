import java.util.List;

public class Admin extends User implements IAdminActions {
    private final ProductCatalog productCatalog;
    private final OrderManager orderManager;
    private final ShoppingCart shoppingCart;

    // Constructor
    public Admin(String username, String password, String name, ProductCatalog productCatalog, OrderManager orderManager) {
        super(username, password, name); // Use the appropriate constructor from the User class
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
        productCatalog.addProduct(product);
        System.out.println("Product added successfully!");
    }

    // Remove a product from the catalog
    @Override
    public void removeProduct(Integer productId) {
        try {
            productCatalog.removeProduct(productId);
            System.out.println("Product removed successfully!");
        } catch (ProductNotFoundException e) {
            System.out.println("Product with ID " + productId + " not found.");
            // Log or handle the exception based on your application's needs
        }
    }

    // Update an existing product
    public void updateProduct(Product product) {
        productCatalog.updateProduct(product);
        System.out.println("Product updated successfully!");
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
        Order newOrder = new Order(products, this);
        orderManager.createOrder(newOrder);
        System.out.println("Order placed successfully!");
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