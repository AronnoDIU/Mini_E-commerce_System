import java.util.InputMismatchException;
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

    // Constructor for ShoppingCart
    public Product(Product product, int quantity) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.stockQuantity = product.getStockQuantity();
    }

    private int getStockQuantity() {
        return stockQuantity;
    }

    private Category getCategory() {
        return category;
    }

    private String getDescription() {
        return description;
    }

    public static Product createProductFromConsoleInput(Scanner scanner) {
        try (scanner) {
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

            validatePrice(price);
            validateStockQuantity(stockQuantity);

            return new Product(null, name, description, price, category, stockQuantity);
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
            /*catching InputMismatchException for issues with mismatched input types,
            and catching IllegalArgumentException for the validation errors.*/
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

    private static void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
    }

    private static void validateStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be non-negative.");
        }
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

    public Product getProduct() {
        return this;
    }

    public double getQuantity() {
        return 0;
    }
}