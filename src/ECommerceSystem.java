import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ECommerceSystem {

    public static Scanner userInput = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(ECommerceSystem.class.getName());
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();
    static User currentUser; // The user that is currently logged in
    static final String PRODUCTS_FILE = "products.txt";
    static final String USERS_FILE = "users.txt";
    static final String ORDERS_FILE = "orders.txt";

    public static void main(String[] args) {
        loadProducts(); // Load the products from the file
        loadUsers(); // Load the users from the file
        loadOrders(); // Load the orders from the file
        showWelcomeMenu(); // Show the welcome menu

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
        userInput.close();
    }

    private static void showWelcomeMenu() {
        System.out.println("Welcome to the E-Commerce System!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("Enter your choice: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                showLoginMenu();
                break;
            case 2:
                showRegisterMenu();
                break;
            case 3:
                System.out.println("Exiting the system...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showWelcomeMenu();
        }
    }

    private static void showRegisterMenu() {
        System.out.println("Enter your name: ");
        String name = userInput.nextLine();
        System.out.println("Enter your username: ");
        String username = userInput.nextLine();
        System.out.println("Enter your password: ");
        String password = userInput.nextLine();
        System.out.println("Enter your address: ");
        String address = userInput.nextLine();
        System.out.println("Enter your email: ");
        String email = userInput.nextLine();
        Authentication authentication = new Authentication(new FileHandler("user_credentials.txt"));
        User user = authentication.register(username, password);
        if (user != null) {
            System.out.println("Registration successful!");
            System.out.println("Welcome, " + user.getUsername() + "!");
            if (user instanceof Admin) {
                showAdminMenu();
            } else if (user instanceof Customer) {
                showCustomerMenu();
            }
        } else {
            System.out.println("Registration failed. Please try again.");
            showRegisterMenu();
        }
    }

    private static void showLoginMenu() {
        System.out.println("Enter your username: ");
        String username = userInput.nextLine();
        System.out.println("Enter your password: ");
        String password = userInput.nextLine();
        Authentication authentication = new Authentication(new FileHandler("user_credentials.txt"));
        User user = authentication.login(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getUsername() + "!");
            if (user instanceof Admin) {
                showAdminMenu();
            } else if (user instanceof Customer) {
                showCustomerMenu();
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
            showLoginMenu();
        }
    }

    private static void showCustomerMenu() {
        System.out.println("1. View products");
        System.out.println("2. View cart");
        System.out.println("3. View order history");
        System.out.println("4. View profile");
        System.out.println("5. Update profile");
        System.out.println("6. Logout");
        System.out.println("Enter your choice: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                showProductsMenu();
                break;
            case 2:
                showCartMenu();
                break;
            case 3:
                showOrderHistoryMenu();
                break;
            case 4:
                currentUser.viewProfile();
                showCustomerMenu();
                break;
            case 5:
                currentUser.updateProfile();
                showCustomerMenu();
                break;
            case 6:
                System.out.println("Logging out...");
                Authentication authentication = new Authentication(new FileHandler("user_credentials.txt"));
                authentication.logout();
                showWelcomeMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showCustomerMenu();
        }
    }

    private static void showOrderHistoryMenu() {
        currentUser.viewOrderHistory();
        showCustomerMenu();
    }

    private static void showCartMenu() {
        currentUser.viewCart();
        System.out.println("Enter the product ID to remove from cart (0 to go back): ");
        int productId = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character
        if (productId == 0) {
            showCustomerMenu();
        } else {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.removeFromCart(productId);
            System.out.println("Product removed from cart successfully!");
            showCustomerMenu();
        }
    }

    private static void showProductsMenu() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.viewProducts();
        System.out.println("Enter the product ID to add to cart (0 to go back): ");
        int productId = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character
        if (productId == 0) {
            showCustomerMenu();
        } else {
            Product product = productCatalog.getProductById(productId);
            if (product != null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.addToCart(product);
                System.out.println("Product added to cart successfully!");
                showCustomerMenu();
            } else {
                System.out.println("Invalid product ID. Please try again.");
                showProductsMenu();
            }
        }
    }

    private static void showAdminMenu() {
        System.out.println("1. Add product");
        System.out.println("2. View product stats");
        System.out.println("3. Manage users");
        System.out.println("4. Logout");
        System.out.println("Enter your choice: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume the newline character
        switch (choice) {
            case 1:
                showAddProductMenu();
                break;
            case 2:
                showProductStatsMenu();
                break;
            case 3:
                showManageUsersMenu();
                break;
            case 4:
                System.out.println("Logging out...");
                Authentication authentication = new Authentication(new FileHandler("user_credentials.txt"));
                authentication.logout();
                showWelcomeMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                showAdminMenu();
        }
    }

    private static void showManageUsersMenu() {
        Admin admin = new Admin("admin", "admin123", "Admin User", new ProductCatalog(), new OrderManager());
        admin.manageUsers();
        showAdminMenu();
    }

    private static void showProductStatsMenu() {
        Admin admin = new Admin("admin", "admin123", "Admin User", new ProductCatalog(), new OrderManager());
        admin.viewProductStats();
        showAdminMenu();
    }

    private static void showAddProductMenu() {
        System.out.println("Enter product name:");
        String name = userInput.nextLine();

        System.out.println("Enter product description:");
        String description = userInput.nextLine();

        System.out.println("Enter product price:");
        double price = userInput.nextDouble();

        System.out.println("Enter product category:");
        String category = userInput.nextLine();

        System.out.println("Enter product stock quantity:");
        int stockQuantity = userInput.nextInt();

        Product product = new Product(null, name, description, price,
                Category.valueOf(category), stockQuantity);
        Admin admin = new Admin("admin", "admin123", "Admin User", new ProductCatalog(), new OrderManager());
        admin.addProduct(product);
        System.out.println("Product added successfully!");
        showAdminMenu();
    }

    private static void loadOrders() {
        FileHandler fileHandler = new FileHandler(ORDERS_FILE);
        orders = fileHandler.readOrders();

        // Print the orders
        System.out.println("Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void loadUsers() {
        FileHandler fileHandler = new FileHandler(USERS_FILE);
        users = fileHandler.readUsers();

        // Print the users
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void loadProducts() {
        FileHandler fileHandler = new FileHandler(PRODUCTS_FILE);
        products = (ArrayList<Product>) FileHandler.readProducts(fileHandler.filePath);

        // Print the products
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}