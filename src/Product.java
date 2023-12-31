import java.util.Scanner;

public class Product {
    private final Integer productId;
    private String name;
    private String description;
    private double price;
    private Category category;
    private int stockQuantity;

    // Constructor
    public Product(Integer productId, String name,
                   String description, double price, Category category, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public static Product createProductFromConsoleInput(Scanner scanner) {
        try {
            System.out.println("Enter product name:");
            String name = scanner.nextLine();

            System.out.println("Enter product description:");
            String description = scanner.nextLine();

            System.out.println("Enter product price:");
            double price = scanner.nextDouble();
            if (price < 0) {
                throw new IllegalArgumentException("Price must be non-negative.");
            }
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter product category:");
            String categoryInput = scanner.nextLine().toUpperCase(); // Convert to uppercase
            Category category = Category.valueOf(categoryInput); // Handle IllegalArgumentException

            System.out.println("Enter product stock quantity:");
            int stockQuantity = scanner.nextInt();
            if (stockQuantity < 0) {
                throw new IllegalArgumentException("Stock quantity must be non-negative.");
            }

            return new Product(null, name, description, price, category, stockQuantity);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid product details.");
            return null; // Indicate failure with null
        }
    }

    // Getters
    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    // toString method for easy representation
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}