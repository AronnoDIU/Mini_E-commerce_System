import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
    private final Map<Integer, Product> products;

    public ProductCatalog() {
        this.products = new HashMap<>();
    }

    // Add a product
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    // Remove a product
    public void removeProduct(Integer productId) {
        products.remove(productId);
    }

    // Update an existing product
    public void updateProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    // Retrieve a product by ID
    public Product getProduct(Integer productId) {
        return products.get(productId);
    }

    // Retrieve all products
    public Map<Integer, Product> getAllProducts() {
        return new HashMap<>(products);
    }

    public void viewProductStatistics() {
        // View product statistics
        System.out.println("Product statistics viewed by admin.");
    }
}