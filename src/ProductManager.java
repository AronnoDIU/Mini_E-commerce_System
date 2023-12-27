import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductManager {
    private final List<Product> products;

    // Constructor
    public ProductManager() {
        this.products = new ArrayList<>();
    }

    // Add a new product
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully. Product ID: " + product.getProductId());
    }

    // Remove an existing product
    public void removeProduct(int productId) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == productId) {
                iterator.remove();
                System.out.println("Product removed successfully. Product ID: " + productId);
                return;
            }
        }
        System.out.println("Product not found. Unable to remove.");
    }

    // Update the details of an existing product
    public void updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getProductId() == updatedProduct.getProductId()) {
                products.set(i, updatedProduct);
                System.out.println("Product updated successfully. Product ID: " + updatedProduct.getProductId());
                return;
            }
        }
        System.out.println("Product not found. Unable to update.");
    }

    // Search for a product by its ID
    public Product searchProduct(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    // Getters and setters (if needed)
    // ...

    // Other methods for product management
    // ...
}