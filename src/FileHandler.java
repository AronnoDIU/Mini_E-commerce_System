import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    final String filePath;

    // Constructor
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Read content from a file
    public String readFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        return content.toString();
    }

    // Write content to a file
    public void writeFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Write to file successful.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static List<Product> readProducts(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productDetails = line.split(",");
                Integer productId = Integer.parseInt(productDetails[0]);
                String name = productDetails[1];
                String description = productDetails[2];
                double price = Double.parseDouble(productDetails[3]);
                Category category = Category.valueOf(productDetails[4]);
                int stockQuantity = Integer.parseInt(productDetails[5]);
                Product product = new Product(productId, name, description, price, category, stockQuantity);
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        return products;
    }

    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                String username = userDetails[0];
                String password = userDetails[1];
                String role = userDetails[2];
                User user = new User(username, password, role);
                users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        return users;
    }

    public ArrayList<Order> readOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderDetails = line.split(",");
                Integer orderId = Integer.parseInt(orderDetails[0]);
                String username = orderDetails[1];
                String products = orderDetails[2];
                Order order = new Order(orderId, username, products);
                orders.add(order);
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        return orders;
    }
}
