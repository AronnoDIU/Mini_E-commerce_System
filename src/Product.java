import java.util.Scanner;

public class Product {
    private Integer productId;
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
        System.out.println("Enter product name:");
        String name = scanner.nextLine();

        System.out.println("Enter product description:");
        String description = scanner.nextLine();

        System.out.println("Enter product price:");
        double price = scanner.nextDouble();

        System.out.println("Enter product category:");
        String category = scanner.nextLine();

        System.out.println("Enter product stock quantity:");
        int stockQuantity = scanner.nextInt();

        return new Product(null, name, description, price,
                Category.valueOf(category), stockQuantity);
    }

    // Getters
    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    // Setters
    public void setProductId(Integer productId) {
        this.productId = productId;
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
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}