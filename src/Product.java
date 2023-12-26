import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Product {
    private static final Logger logger = Logger.getLogger(Product.class.getName());
    private static final String PRODUCTS_FILE = "products.txt";

    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        saveProduct();
    }

    public static Product parseProduct(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        return new Product(name, price, quantity);
    }

    public static Product loadProduct(String productName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(PRODUCTS_FILE));
            for (String line : lines) {
                Product product = parseProduct(line);
                if (product.getName().equals(productName)) {
                    return product;
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading products from file: " + e.getMessage());
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
        saveProduct();
    }

    public void setPrice(double price) {
        this.price = price;
        saveProduct();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        saveProduct();
    }

    private void saveProduct() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Name: " + name);
            lines.add("Price: " + price);
            lines.add("Quantity: " + quantity);
            lines.add(""); // Add a blank line to separate products
            Files.write(Path.of(PRODUCTS_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing product to file: " + e.getMessage());
        }
    }

    public static List<Product> readProducts() {
        try {
            List<Product> products = new ArrayList<>();
            List<String> lines = Files.readAllLines(Path.of(PRODUCTS_FILE));

            String currentName = null;
            double currentPrice = 0;
            int currentQuantity = 0;

            for (String line : lines) {
                if (line.startsWith("Name: ")) {
                    if (currentName != null) {
                        products.add(new Product(currentName, currentPrice, currentQuantity));
                    }
                    currentName = line.substring("Name: ".length());
                } else if (line.startsWith("Price: ")) {
                    currentPrice = Double.parseDouble(line.substring("Price: ".length()));
                } else if (line.startsWith("Quantity: ")) {
                    currentQuantity = Integer.parseInt(line.substring("Quantity: ".length()));
                }
            }

            if (currentName != null) {
                products.add(new Product(currentName, currentPrice, currentQuantity));
            }

            return products;
        } catch (IOException e) {
            logger.severe("Error reading products from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public double getInitialQuantity() {
        return quantity;
    }
}
