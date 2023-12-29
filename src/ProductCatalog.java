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

    public void removeProduct(int productId) throws ProductNotFoundException {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }
        products.remove(productId);
    }

    public void updateProduct(Product product) {
        if (!products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product with ID " + product.getProductId() + " not found.");
        }
        products.put(product.getProductId(), product);
    }

    public Product getProduct(int productId) {
        return products.get(productId);
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

    public void viewProductCatalog() {
        System.out.println("Product catalog viewed by customer:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}