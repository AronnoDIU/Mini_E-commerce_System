import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
    private final Map<Integer, Product> products;

    public ProductCatalog() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    // Remove a product
    public void removeProduct(Integer productId) throws ProductNotFoundException {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        products.remove(productId);
    }

    public void updateProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Product getProduct(Integer productId) {
        return products.get(productId);
    }

    public Map<Integer, Product> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }

    public void viewProductStatistics() {
        System.out.println("Product statistics viewed by admin.");
    }

    public void viewProducts() {
        System.out.println("Products viewed by admin:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public Product getProductById(int productId) {
        return products.get(productId);
    }
}