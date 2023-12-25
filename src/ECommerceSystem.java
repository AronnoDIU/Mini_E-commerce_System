import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceSystem {
    private static final List<Product> products = createSampleProducts();
    private static final List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        users.add(new User("user1", "pass1"));
        users.add(new User("user2", "pass2"));

        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = userInput.nextInt();

            switch (choice) {
                case 1:
                    login(userInput);
                    break;
                case 2:
                    register(userInput);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                showMainMenu(scanner);
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.");
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a new username: ");
        String newUsername = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("Username already exists. Please choose another one.");
                return;
            }
        }

        System.out.print("Enter a password: ");
        String newPassword = scanner.next();
        users.add(new User(newUsername, newPassword));
        System.out.println("Registration successful. You can now log in.");
    }

    private static void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    viewCart(scanner);
                    break;
                case 3:
                    checkout(scanner);
                    break;
                case 4:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() +
                    " - $" + product.getPrice() +
                    " - Category: " + product.getCategory() +
                    " - Stock: " + product.getQuantity());
        }
    }

    private static void viewCart(Scanner scanner) {
        System.out.println("\nItems in your cart:");
        List<Product> cartItems = currentUser.getShoppingCart().getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {
            Product product = cartItems.get(i);
            System.out.println((i + 1) + ". " + product.getName() +
                    " - $" + product.getPrice() +
                    " - Quantity: " + product.getQuantity());
        }
        System.out.println("Total: $" + currentUser.getShoppingCart().calculateTotal());
        System.out.println("1. Remove item from cart");
        System.out.println("2. Clear cart");
        System.out.println("3. Go back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                removeItemFromCart(scanner);
                break;
            case 2:
                currentUser.getShoppingCart().clearCart();
                System.out.println("Cart cleared.");
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void removeItemFromCart(Scanner scanner) {
        List<Product> cartItems = currentUser.getShoppingCart().getCartItems();
        System.out.print("Enter the number of the item to remove: ");
        int itemNumber = scanner.nextInt();

        if (itemNumber > 0 && itemNumber <= cartItems.size()) {
            Product removedProduct = cartItems.get(itemNumber - 1);
            Product originalProduct = getProductByName(removedProduct.getName());

            assert originalProduct != null;
            originalProduct.decreaseQuantity(removedProduct.getQuantity());
            cartItems.remove(itemNumber - 1);

            System.out.println(removedProduct.getName() + " removed from cart.");
        } else {
            System.out.println("Invalid item number. Please try again.");
        }
    }

    private static void checkout(Scanner scanner) {
        List<Product> cartItems = currentUser.getShoppingCart().getCartItems();

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checking out.");
            return;
        }

        System.out.println("\nItems in your cart:");
        for (int i = 0; i < cartItems.size(); i++) {
            Product product = cartItems.get(i);
            System.out.println((i + 1) + ". " + product.getName() +
                    " - $" + product.getPrice() +
                    " - Quantity: " + product.getQuantity());
        }

        System.out.println("Total: $" + currentUser.getShoppingCart().calculateTotal());
        System.out.println("1. Confirm and place order");
        System.out.println("2. Cancel and go back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                placeOrder();
                currentUser.getShoppingCart().clearCart();
                System.out.println("Order placed successfully. Thank you!");
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void placeOrder() {
        // In a real-world scenario, you might want to implement order processing and storage.
        // For simplicity, this example does not handle orders beyond clearing the shopping cart.
    }

    private static Product getProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    private static List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", "Electronics", 999.99, 10));
        products.add(new Product("Smartphone", "Electronics", 299.99, 20));
        products.add(new Product("Headphones", "Electronics", 49.99, 30));
        products.add(new Product("T-shirt", "Clothing", 19.99, 50));
        products.add(new Product("Jeans", "Clothing", 39.99, 25));
        products.add(new Product("Book", "Books", 9.99, 100));
        return products;
    }
}
