import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {

    private static final String USER_FILE = "users.txt";
    private static final String PRODUCT_FILE = "products.txt";

    private List<User> users;
    private List<Product> products;

    public ECommerceSystem() {
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        loadUsers();
        loadProducts();
    }

    // Methods for User Operations

    public void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Methods for Product Operations

    public void addProduct(Product product) {
        products.add(product);
        saveProductsToFile();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    // Methods for File Operations

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseUser(line);
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
    }

    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.parseProduct(line);
                products.add(product);
            }
        } catch (IOException e) {
            System.err.println("Error reading product file: " + e.getMessage());
        }
    }

    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user file: " + e.getMessage());
        }
    }

    private void saveProductsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing product file: " + e.getMessage());
        }
    }

    // Other methods for business logic can be added as needed

    public static void main(String[] args) {
        // Example usage of the ECommerceSystem class
        ECommerceSystem ecommerceSystem = new ECommerceSystem();

        // Creating a new user
        User newUser = new User("john_doe", "password123", "john@example.com");
        ecommerceSystem.addUser(newUser);

        // Retrieving a user by username
        User retrievedUser = ecommerceSystem.getUserByUsername("john_doe");
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser.getUsername());
        } else {
            System.out.println("User not found.");
        }

        // Adding a product
        Product newProduct = new Product("Laptop", 999.99, 10);
        ecommerceSystem.addProduct(newProduct);

        // Retrieving all products
        List<Product> allProducts = ecommerceSystem.getAllProducts();
        System.out.println("All Products:");
        for (Product product : allProducts) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }
}
