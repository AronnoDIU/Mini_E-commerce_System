import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Inventory class represents a system for managing product stock levels and suppliers.
 */
public class Inventory {
    private Map<Product, Integer> stockLevels;
    private Map<Product, String> suppliers;

    // Constructors
    public Inventory() {
        this.stockLevels = new HashMap<>();
        this.suppliers = new HashMap<>();
    }

    // Getters and Setters
    public Map<Product, Integer> getStockLevels() {
        return stockLevels;
    }

    public void setStockLevels(Map<Product, Integer> stockLevels) {
        this.stockLevels = stockLevels;
    }

    public Map<Product, String> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Map<Product, String> suppliers) {
        this.suppliers = suppliers;
    }

    /**
     * Adds a product to the inventory with the specified initial stock level and supplier.
     *
     * @param product      The product to add.
     * @param initialStock The initial stock level.
     * @param supplier     The supplier of the product.
     */
    public void addProductToInventory(Product product, int initialStock, String supplier) {
        stockLevels.put(product, initialStock);
        suppliers.put(product, supplier);
        System.out.println("Product '" + product.getName() + "' added to inventory. Initial stock level set to " + initialStock);
    }

    /**
     * Updates the stock level for a product.
     *
     * @param product  The product to update.
     * @param quantity The quantity to add or subtract from the stock level.
     */
    public void updateStockLevel(Product product, int quantity) {
        int currentStock = stockLevels.getOrDefault(product, 0);
        stockLevels.put(product, currentStock + quantity);
        System.out.println("Stock level updated for '" + product.getName() + "'. New stock level: " + stockLevels.get(product));

        // Generate alert for low stock
        if (stockLevels.get(product) <= 5) {
            System.out.println("Alert: Low stock for product '" + product.getName() + "'. Current stock: " + stockLevels.get(product));
        }
    }

    public void displayInventory() {
        System.out.println("Inventory Contents:");
        for (Map.Entry<Product, Integer> entry : stockLevels.entrySet()) {
            Product product = entry.getKey();
            int stockLevel = entry.getValue();
            System.out.println("Product: " + product.getName() + ", Stock Level: " + stockLevel);
        }
    }

    public void displaySuppliers() {
        System.out.println("Suppliers Information:");
        for (Map.Entry<Product, String> entry : suppliers.entrySet()) {
            Product product = entry.getKey();
            String supplier = entry.getValue();
            System.out.println("Product: " + product.getName() + ", Supplier: " + supplier);
        }
    }

    /**
     * Creates an inventory by taking user inputs from the console.
     *
     * @param scanner The Scanner object for user input.
     * @return The created Inventory.
     */
    public static Inventory createInventoryFromConsoleInput(Scanner scanner) {
        Inventory inventory = new Inventory();

        // Input for adding initial products to the inventory
        System.out.println("Enter the number of initial products to add to the inventory:");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numProducts; i++) {
            Product product = Product.createProductFromConsoleInput(scanner);
            System.out.println("Enter the initial stock level for " + product.getName() + ":");
            int initialStock = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter the supplier for " + product.getName() + ":");
            String supplier = scanner.nextLine();

            inventory.addProductToInventory(product, initialStock, supplier);
        }

        return inventory;
    }
}