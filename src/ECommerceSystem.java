import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ECommerceSystem {
    private static final Logger logger = Logger.getLogger(ECommerceSystem.class.getName());
    private static final List<Product> products = createSampleProducts();
    private static final List<User> users = new ArrayList<>();
    static ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static User currentUser; // The user that is currently logged in
    static final String PRODUCTS_FILE = "products.txt";
    static final String USERS_FILE = "users.txt";
    static final String ShoppingCart_FILE = "shoppingCarts.txt";

    public static void main(String[] args) {
        loadAll();

        users.add(new User("user1", "pass1"));
        users.add(new User("user2", "pass2"));

        Scanner userInput = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the E-Commerce System!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = userInput.nextInt();

            switch (choice) {
                case 1: // For User Login
                    login(userInput);
                    break;
                case 2: // For User Registration
                    register(userInput);
                    break;
                case 3: // For User Exit
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default: // For Invalid Choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner userInput) {
        System.out.print("Enter your username: ");
        String username = userInput.next();
        System.out.print("Enter your password: ");
        String password = userInput.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                showMainMenu(userInput);
                return;
            }
        }

        System.out.println("Invalid username or password. Please try again.");
    }

    private static void register(Scanner userInput) {
        System.out.print("Enter a new username: ");
        String newUsername = userInput.next();

        for (User user : users) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("Username already exists. Please choose another one.");
                return;
            }
        }

        System.out.print("Enter a password: ");
        String newPassword = userInput.next();
        users.add(new User(newUsername, newPassword));
        System.out.println("Registration successful. You can now log in.");
    }

    private static void showMainMenu(Scanner userInput) {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. View Products");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = userInput.nextInt();

            switch (choice) {
                case 1: // For Viewing Products
                    displayProducts();
                    break;
                case 2: // For Viewing Cart
                    viewCart(userInput);
                    break;
                case 3: // For Checkout
                    checkout(userInput);
                    break;
                case 4: // For Logout
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

    private static void viewCart(Scanner userInput) {
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
        int choice = userInput.nextInt();

        switch (choice) {
            case 1:
                removeItemFromCart(userInput);
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

    private static void removeItemFromCart(Scanner userInput) {
        List<Product> cartItems = currentUser.getShoppingCart().getCartItems();
        System.out.print("Enter the number of the item to remove: ");
        int itemNumber = userInput.nextInt();

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

    private static void checkout(Scanner userInput) {
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
        int choice = userInput.nextInt();

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

    private static void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.add(shoppingCart);
    }

    private static void removeShoppingCart(ShoppingCart shoppingCart) {
        shoppingCarts.remove(shoppingCart);
    }

    private static void updateShoppingCart(ShoppingCart shoppingCart) {
        int index = shoppingCarts.indexOf(shoppingCart);
        shoppingCarts.set(index, shoppingCart);
    }

    private static void saveShoppingCarts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("shoppingCarts.bin"))) {
            oos.writeObject(shoppingCarts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadShoppingCarts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("shoppingCarts.bin"))) {
            shoppingCarts = (ArrayList<ShoppingCart>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveProducts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PRODUCTS_FILE))) {
            for (Product product : products) {
                pw.println(product.getName() + "," + product.getCategory() + "," + product.getPrice() + "," + product.getQuantity());
            }
        } catch (IOException e) {
            logger.warning("Error saving products to file.");
        }
    }

    private static void loadProducts() {
        try (Scanner scanner = new Scanner(new File(PRODUCTS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                products.add(new Product(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3])));
            }
        } catch (FileNotFoundException e) {
            logger.warning("Error loading products from file.");
        }
    }

    private static void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                pw.println(user.getUsername() + "," + user.getPassword());
            }
        } catch (IOException e) {
            logger.warning("Error saving users to file.");
        }
    }

    private static void loadUsers() {
        try (Scanner scanner = new Scanner(new File(USERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1]));
            }
        } catch (FileNotFoundException e) {
            logger.warning("Error loading users from file.");
        }
    }

    private static void saveShoppingCart() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ShoppingCart_FILE))) {
            for (ShoppingCart shoppingCart : shoppingCarts) {
                pw.println(shoppingCart.getCartItems());
            }
        } catch (IOException e) {
            logger.warning("Error saving shoppingCart to file.");
        }
    }

    private static void loadShoppingCart() {
        try (Scanner scanner = new Scanner(new File(ShoppingCart_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                shoppingCarts.add(new ShoppingCart());
            }
        } catch (FileNotFoundException e) {
            logger.warning("Error loading shoppingCart from file.");
        }
    }

    private static void saveAll() {
        saveProducts();
        saveUsers();
        saveShoppingCart();
    }

    private static void loadAll() {
        loadProducts();
        loadUsers();
        loadShoppingCart();
    }
}
