import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Admin {
    private static final Logger logger = Logger.getLogger(Admin.class.getName());
    private static final String PRODUCT_FILE = "products.txt";
    private static final String USER_FILE = "users.txt";

    static void addProduct(Product product, List<Product> productList) {
        productList.add(product);
        System.out.println("Product added successfully: " + product.getName());
        saveProducts(productList);
    }

    static void removeProduct(Product product, List<Product> productList) {
        if (productList.contains(product)) {
            productList.remove(product);
            System.out.println("Product removed successfully: " + product.getName());
            saveProducts(productList);
        } else {
            System.out.println("Product not found in the inventory: " + product.getName());
        }
    }

    public static void viewInventory(List<Product> productList) {
        System.out.println("Inventory:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    static void generateSalesReport(List<Product> productList) {
        double totalSales = 0;
        System.out.println("Sales Report:");
        for (Product product : productList) {
            double productSales = product.getPrice() * (product.getInitialQuantity() - product.getQuantity());
            totalSales += productSales;
            System.out.println(product.getName() + ": $" + productSales);
        }
        System.out.println("Total Sales: $" + totalSales);
    }

    public static void registerUser(String username, String password) {
        AuthenticationManager.registerUser(username, password);
        saveUsers(AuthenticationManager.getUserCredentials());
    }

    private static void saveProducts(List<Product> productList) {
        try {
            List<String> lines = new ArrayList<>();
            for (Product product : productList) {
                lines.add(product.toString());
            }
            Files.write(Paths.get(PRODUCT_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing products to file: " + e.getMessage());
        }
    }

    private static void saveUsers(Map<String, String> userCredentials) {
        try {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                lines.add(entry.getKey() + "," + entry.getValue());
            }
            Files.write(Paths.get(USER_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing users to file: " + e.getMessage());
        }
    }
}
