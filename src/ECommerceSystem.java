import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {
    private final ProductManager productManager;
    private final UserManager userManager;
    private final ProductCatalog productCatalog;
    static User currentUser;
    private final Scanner scanner;

    public ECommerceSystem() {
        this.productManager = new ProductManager();
        this.userManager = new UserManager();
        this.productCatalog = new ProductCatalog();
        currentUser = null; // No user is logged in initially
        this.scanner = new Scanner(System.in);
        loadInitialProducts();
    }

    public static void main(String[] args) {
        ECommerceSystem ecommerceSystem = new ECommerceSystem();
        ecommerceSystem.run();
    }

    private void displayMainMenu() {
        System.out.println("ECommerce System Main Menu");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Remove from Cart");
        System.out.println("5. Place Order");
        System.out.println("6. View Order History");
        System.out.println("7. View Profile");
        System.out.println("8. Update Profile");
        System.out.println("9. Apply Discount");
        System.out.println("10. View Product Catalog");
        System.out.println("11. Manage Products (Admin)");
        System.out.println("12. View Product Statistics (Admin)");
        System.out.println("13. Manage Users (Admin)");
        System.out.println("14. Logout");
        System.out.println("15. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
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
                    case 1: // For View Products
                        viewProducts();
                        break;
                    case 2: // For Add to Cart
                        addToCart();
                        break;
                    case 3: // For View Cart
                        viewCart();
                        break;
                    case 4: // For Remove from Cart (customer only)
                        if (currentUser instanceof Customer) {
                            removeFromCart();
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 5: // For Place Order
                        placeOrder();
                        break;
                    case 6: // For View Order History
                        viewOrderHistory();
                        break;
                    case 7: // For View Profile
                        viewProfile();
                        break;
                    case 8: // For Update Profile
                        updateProfile();
                        break;
                    case 9: // For Apply Discount
                        applyDiscount();
                        break;
                    case 10: // For View Product Catalog
                        viewProductCatalog();
                        break;
                    case 11: // For Manage Products (Admin only)
                        if (currentUser instanceof Admin) {
                            manageProducts();
                        } else {
                            System.out.println("You do not have permission to access this feature.");
                        }
                        break;
                    case 12: // For View Product Statistics (Admin)
                        if (currentUser instanceof Admin) {
                            productCatalog.viewProductStatistics();
                        } else {
                            System.out.println("You do not have permission to access this feature.");
                        }
                        break;
                    case 13: // For Manage Users (Admin only)
                        if (currentUser instanceof Admin) {
                            userManager.manageUsers();
                        } else {
                            System.out.println("You do not have permission to access this feature.");
                        }
                        break;
                    case 14: // For Logout
                        logout();
                        break;
                    case 15: // For Exit
                        exit();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private void manageProducts() {
        // Example: Managing products
        while (true) {
            System.out.println("Product Management Menu");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. Search Product");
            System.out.println("5. View All Products");
            System.out.println("6. Back to the Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // For Add Product
                    addProduct();
                    break;
                case 2: // For Remove Product
                    removeProduct();
                    break;
                case 3: // For Update Product
                    updateProduct();
                    break;
                case 4: // For Search Product
                    searchProduct();
                    break;
                case 5: // For View All Products
                    viewAllProducts();
                    break;
                case 6: // For Back to the Main Menu
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    manageProducts();
            }
        }
    }

    private void addProduct() {
        System.out.println("Enter Product details to add:");

        System.out.println("Enter product ID: ");
        int productId = scanner.nextInt();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter product category: ");
        String category = scanner.nextLine().toUpperCase();

        System.out.print("Enter product stock quantity: ");
        int stockQuantity = scanner.nextInt();

        Product newProduct = new Product(productId, name,
                description, price, Category.valueOf(category), stockQuantity);
        productManager.addProduct(newProduct);

        System.out.println("Product added successfully.");
    }


    private void removeProduct() {
        System.out.println("Enter Product details to remove:");

        System.out.print("Enter the Product ID to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        productManager.removeProduct(productId);
    }

    private void updateProduct() {
        System.out.println("Enter Product details to update:");

        System.out.print("Enter the Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product existingProduct = productManager.searchProduct(productId);

        if (existingProduct != null) {
            System.out.print("Enter updated product name (press Enter to keep current name): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                existingProduct.setName(newName);
            }

            System.out.print("Enter updated product description (press Enter to keep current description): ");
            String newDescription = scanner.nextLine();
            if (!newDescription.isEmpty()) {
                existingProduct.setDescription(newDescription);
            }

            System.out.print("Enter updated product price (press Enter to keep current price): ");
            String newPriceString = scanner.nextLine();
            if (!newPriceString.isEmpty()) {
                double newPrice = Double.parseDouble(newPriceString);
                existingProduct.setPrice(newPrice);
            }

            System.out.print("Enter updated product category (press Enter to keep current category): ");
            String newCategoryString = scanner.nextLine();
            if (!newCategoryString.isEmpty()) {
                existingProduct.setCategory(Category.valueOf(newCategoryString.toUpperCase()));
            }

            System.out.print("Enter updated product stock quantity (press Enter to keep current stock quantity): ");
            String newStockQuantityString = scanner.nextLine();
            if (!newStockQuantityString.isEmpty()) {
                int newStockQuantity = Integer.parseInt(newStockQuantityString);
                existingProduct.setStockQuantity(newStockQuantity);
            }

            productManager.updateProduct(existingProduct);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void searchProduct() {
        System.out.println("Enter Product details to search:");

        System.out.print("Enter the Product ID to search: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Product foundProduct = productManager.searchProduct(productId);

        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewAllProducts() {
        System.out.println("Viewing all products:");

        List<Product> allProducts = productManager.getAllProducts();

        if (allProducts.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("All Products:");
            for (Product product : allProducts) {
                System.out.println(product);
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
        System.out.println("1. Log in to your account");
        System.out.println("2. Create Account (New User)");
        System.out.println("3. Exit the system");
        System.out.print("Enter your choice: ");
    }

    private void authenticateUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (userManager.authenticateUser(username, password)) {
            currentUser = userManager.getUserByUsername(username);
            System.out.println("Authentication successful. Welcome, " + currentUser.getName() + "!");
        } else {
            System.out.println("Authentication failed. Please check your credentials.");
        }
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

    private void removeFromCart() {
        if (currentUser instanceof Customer) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the product ID to remove from the cart: ");
            int productId = scanner.nextInt();
            ((Customer) currentUser).getShoppingCart().removeFromCart(productId);
        } else {
            System.out.println("Invalid user type for this operation.");
        }
    }


    private void placeOrder() {
        // Example: Placing an order
        List<Product> cartItems = currentUser.viewCart();
        double totalAmount = currentUser.calculateTotal();

        // Display order summary
        System.out.println("Order Summary:");
        currentUser.viewCart();
        System.out.println("Total Amount: $" + totalAmount);

        // Process payment
        boolean paymentSuccess = PaymentProcessor.processPayment(totalAmount, scanner);

        if (paymentSuccess) {
            // If payment is successful, proceed with order placement
            currentUser.placeOrder(cartItems);
        } else {
            System.out.println("Order placement failed due to payment issues.");
        }
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

    private void exit() {
        // Example: Exiting the system
        System.out.println("Exiting the ECommerce System. Goodbye!");
        scanner.close();
        System.exit(0);
    }
}