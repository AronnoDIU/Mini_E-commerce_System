import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

//import static jdk.internal.misc.OSEnvironment.initialize;

public class ECommerceSystem {
    private final ProductCatalog productCatalog;
    private static User currentUser;
    private final Scanner scanner;

    public ECommerceSystem() {
        this.productCatalog = new ProductCatalog();
        currentUser = null; // Initialize as needed
        this.scanner = new Scanner(System.in);
    }
    private void displayAuthenticationMenu() {
        System.out.println("Authentication Menu:");
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

        // Example: Simple authentication (replace with your authentication logic)
        if (authenticate(username, password)) {
            assert currentUser != null;
            System.out.println("Authentication successful. Welcome, " +
                    currentUser.getName() + "!");
        } else {
            System.out.println("Authentication failed. Please check your credentials.");
        }
    }
    private User getUserByUsername(String username) {
        // Example: Retrieve user from your data store (replace with your logic)
        // This is where you would query your database or other storage mechanism
        // to get the user based on the provided username.
        // For simplicity, we assume a default user for this example.
        return new User("John Doe", "john.doe", "password", "123 Main St", "john.doe@example.com");
    }

    private boolean authenticate(String username, String password) {
        // Example: Simple authentication logic (replace with your authentication logic)
        // Here, we assume the existence of a method getUserByUsername in your system
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
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

        // Example: Create a new user account (replace with your logic)
        // Here, you might want to check if the username is unique and
        // perform other validation checks before creating the account.
        currentUser = new User(name, username, password, address, email);
        System.out.println("Account created successfully. Welcome, " + currentUser.getName() + "!");
    }
    private void logout() {
        System.out.println("Logging out. Goodbye, " + currentUser.getName() + "!");
        currentUser = null;
    }


    public void run() {
        // Initialize the system, load products, authenticate users, etc.

        // Example: Loading initial products
        loadInitialProducts();

        // Example: Simulate user interaction
        simulateUserInteraction();

        System.out.println("Welcome to the ECommerce System!");

        while (true) {
            displayAuthenticationMenu();

            int authChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (authChoice) {
                case 1:
                    authenticateUser();
                    break;
                case 2:
                    createUserAccount();
                    break;
                case 3:
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
                        logout();
                        break;
                    case 9:
                        exit();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private void loadInitialProducts() {
        // Example: Adding some initial products to the catalog
        Product product1 = new Product(1, "Laptop", "Powerful laptop", 1200.0, Category.ELECTRONICS, 10);
        Product product2 = new Product(2, "Book", "Bestseller book", 20.0, Category.BOOKS, 50);

        productCatalog.addProduct(product1);
        productCatalog.addProduct(product2);
    }

    private void simulateUserInteraction() {
        // Example: Simulating user interaction
        while (true) {
            displayMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
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
                    exit();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        // Example: Displaying the main menu
        System.out.println("Main Menu:");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Place Order");
        System.out.println("5. View Order History");
        System.out.println("6. View Profile");
        System.out.println("7. Update Profile");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private void viewProducts() {
        // Example: Displaying products from the catalog
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
        // Example: Viewing the user's shopping cart
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
    public static void main(String[] args) throws IOException {
        ECommerceSystem ecommerceSystem = new ECommerceSystem();
        ecommerceSystem.run();
    }
}