import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ECommerceSystem {
    private final ProductCatalog productCatalog;
    private static User currentUser;
    private final Scanner scanner;

    public ECommerceSystem() {
        this.productCatalog = new ProductCatalog();
        currentUser = null; // No user is logged in initially
        this.scanner = new Scanner(System.in);
        loadInitialProducts();
    }

    public static void main(String[] args) throws ProductNotFoundException {
        ECommerceSystem ecommerceSystem = new ECommerceSystem();
        ecommerceSystem.run();
    }

    public void run() throws ProductNotFoundException {
        displayWelcomeMessage();

        while (true) {
            displayAuthenticationMenu(); // Display the authentication menu

            int authChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (authChoice) {
                case 1: // For Log in
                    authenticateUser();
                    break;
                case 2: // For Create Account
                    createUserAccount();
                    break;
                case 3: // For Exit
                    exit();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (currentUser != null) {
                // If user successfully authenticated or created, proceed to the main menu
                displayMainMenu();
                int mainMenuChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (mainMenuChoice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        addToCart();
                        break;
                    case 3:
                        viewCart();
                        break;
                    case 4:
                        placeOrder();
                        break;
                    case 5:
                        viewOrderHistory();
                        break;
                    case 6:
                        viewProfile();
                        break;
                    case 7:
                        updateProfile();
                        break;
                    case 8:
                        applyDiscount();
                        break;
                    case 9:
                        viewProductCatalog();
                        break;
                    case 10:
                        if (currentUser instanceof Admin) {
                            manageProducts();
                        } else {
                            System.out.println("You do not have permission to access this feature.");
                        }
                        break;
                    case 11:
                        if (currentUser instanceof Admin) {
                            productCatalog.viewProductStatistics();
                        } else {
                            System.out.println("You do not have permission to access this feature.");
                        }
                        break;
                    case 12:
                        logout();
                        break;
                    case 13:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private void manageProducts() throws ProductNotFoundException {
        // Example: Managing products
        while (true) {
            System.out.println("Manage Products Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. View Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // For Add Product
                    productCatalog.addProduct(Product.createProductFromConsoleInput(scanner));
                    break;
                case 2: // For Remove Product
                    productCatalog.removeProduct(Objects.requireNonNull(Product.createProductFromConsoleInput(scanner)).getProductId());
                    break;
                case 3: // For Update Product
                    productCatalog.updateProduct(Objects.requireNonNull(Product.createProductFromConsoleInput(scanner)));
                    break;
                case 4: // For View Products
                    viewProducts();
                    break;
                case 5: // For Exit
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewProductCatalog() {
        productCatalog.viewProductCatalog();
    }

    private void applyDiscount() {
        System.out.println("Enter discount percentage:");
        double discountPercentage = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        currentUser.applyDiscount(discountPercentage);
    }

    private void loadInitialProducts() {
        // Example: Adding some initial products to the catalog
        Product product1 = new Product(1, "Laptop", "Powerful laptop", 1200.0, Category.ELECTRONICS, 10);
        Product product2 = new Product(2, "Book", "Bestseller book", 20.0, Category.BOOKS, 50);
        Product product3 = new Product(3, "Phone", "Latest smartphone", 800.0, Category.ELECTRONICS, 15);
        Product product4 = new Product(4, "Tablet", "High resolution tablet", 400.0, Category.ELECTRONICS, 20);
        Product product5 = new Product(5, "Headphones", "Noise cancelling headphones", 150.0, Category.ELECTRONICS, 30);
        Product product6 = new Product(6, "Keyboard", "Wireless keyboard", 60.0, Category.ELECTRONICS, 25);
        Product product7 = new Product(7, "Mouse", "Wireless mouse", 40.0, Category.ELECTRONICS, 25);
        Product product8 = new Product(8, "Monitor", "4k monitor", 300.0, Category.ELECTRONICS, 10);
        Product product9 = new Product(9, "Speakers", "Bluetooth speakers", 100.0, Category.ELECTRONICS, 20);
        Product product10 = new Product(10, "Smart Watch", "Fitness smart watch", 200.0, Category.ELECTRONICS, 15);

        productCatalog.addProduct(product1);
        productCatalog.addProduct(product2);
        productCatalog.addProduct(product3);
        productCatalog.addProduct(product4);
        productCatalog.addProduct(product5);
        productCatalog.addProduct(product6);
        productCatalog.addProduct(product7);
        productCatalog.addProduct(product8);
        productCatalog.addProduct(product9);
        productCatalog.addProduct(product10);
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to the E-Commerce System!");
    }

    private void displayAuthenticationMenu() {
        System.out.println("Welcome to the E-Commerce System!");
        System.out.println("1. Log In");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void authenticateUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (authenticate(username, password)) {
            assert currentUser != null;
            System.out.println("Authentication successful. Welcome, " +
                    currentUser.getName() + "!");
        } else {
            System.out.println("Authentication failed. Please check your credentials.");
        }
    }

    private void displayMainMenu() {
        System.out.println("ECommerce System Main Menu");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Place Order");
        System.out.println("5. View Order History");
        System.out.println("6. View Profile");
        System.out.println("7. Update Profile");
        System.out.println("8. Apply Discount");
        System.out.println("9. View Product Catalog");
        System.out.println("10. Manage Products (Admin)");
        System.out.println("11. View Product Statistics (Admin)");
        System.out.println("12. Logout");
        System.out.println("13. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewProducts() {
        productCatalog.viewProducts();
    }

    private void addToCart() {
        // Example: Adding a product to the cart
        System.out.println("Enter the product ID to add to the cart: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product product = productCatalog.getProduct(productId);
        if (product != null) {
            currentUser.addToCart(product);
            System.out.println(product.getName() + " added to the cart.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewCart() {
        // Viewing the user's shopping cart
        currentUser.viewCart();
    }

    private void placeOrder() {
        // Example: Placing an order
        List<Product> cartItems = currentUser.viewCart();
        currentUser.placeOrder(cartItems);
    }

    private void viewOrderHistory() {
        // Example: Viewing the user's order history
        List<Order> orderHistory = currentUser.viewOrderHistory();
        for (Order order : orderHistory) {
            System.out.println(order);
        }
    }

    private void viewProfile() {
        // Example: Viewing user profile
        currentUser.viewProfile();
    }

    private void updateProfile() {
        // Example: Updating user profile
        currentUser.updateProfile();
    }

    private void exit() {
        // Example: Exiting the system
        System.out.println("Exiting the ECommerce System. Goodbye!");
        scanner.close();
        System.exit(0);
    }

    private User getUserByUsername(String username) {
        return new User("John Doe", "john.doe", "password",
                "123 Main St", "john.doe@example.com");
    }

    private boolean authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    private void createUserAccount() {
        // Example: Creating a new user account
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Create a new user account
        currentUser = new User(name, username, password, address, email);
        System.out.println("Account created successfully. Welcome, " +
                currentUser.getName() + "!");
    }

    private void logout() {
        System.out.println("Logging out. Goodbye, " + currentUser.getName() + "!");
        currentUser = null;
    }
}