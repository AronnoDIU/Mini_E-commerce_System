import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create instances of required components
        FileHandler fileHandler = new FileHandler("user_credentials.txt");
        Authentication authentication = new Authentication(fileHandler);
        ProductCatalog productCatalog = new ProductCatalog();
        OrderManager orderManager = new OrderManager();
        Inventory inventory = new Inventory();
        ShoppingCart shoppingCart = new ShoppingCart();

        // Create some initial products and add them to the inventory
        Product product1 = new Product(1, "Laptop", "Powerful laptop", 999.99, Category.ELECTRONICS, 20);
        Product product2 = new Product(2, "T-Shirt", "Comfortable cotton t-shirt", 19.99, Category.CLOTHING, 50);
        inventory.addProductToInventory(product1, 10, "Electronics Supplier");
        inventory.addProductToInventory(product2, 30, "Fashion Supplier");

        // Create an Admin user
        Admin admin = new Admin("admin", "admin123", "Admin User", productCatalog, orderManager);

        // Create a Customer user
        Customer customer = new Customer("123456", "customer1", "password123", "123 Main St", "customer@example.com");

        // Register a new user (Customer)
        authentication.register("customer2", "password456");

        // Login as the admin
        authentication.login("admin", "admin123");

        // Admin actions
        admin.addProduct(new Product(3, "Smartphone", "High-end smartphone", 699.99, Category.ELECTRONICS, 15));
        admin.viewProductStats();
        admin.manageUsers();

        // Logout from an admin account
        authentication.logout();

        // Login as a customer
        authentication.login("customer1", "password123");

        // Customer actions
        customer.addToCart(product1);
        customer.addToCart(product2);
        customer.viewCart();
        customer.placeOrder(shoppingCart.displayCart());

        // View order history
        List<Order> orderHistory = customer.viewOrderHistory();
        System.out.println("Order History:");
        for (Order order : orderHistory) {
            System.out.println(order);
        }

        // Logout from a customer account
        authentication.logout();

        // Close the scanner
        scanner.close();
    }
}