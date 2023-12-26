import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class ShoppingCart {
    private static final Logger logger = Logger.getLogger(ShoppingCart.class.getName());
    private static final String CART_FILE = "shopping_cart.txt";

    private Map<String, Integer> cartItems;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        loadCart();
    }

    public void addItem(String product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        saveCart();
    }

    public void removeItem(String product) {
        cartItems.remove(product);
        saveCart();
    }

    public void viewCart() {
        System.out.println("Shopping Cart:");
        cartItems.forEach((product, quantity) -> System.out.println(product + ": " + quantity));
    }

    private void loadCart() {
        try {
            Path path = Path.of(CART_FILE);
            if (Files.exists(path)) {
                try (var linesStream = Files.lines(path)) {
                    Map<String, Integer> loadedCart = new HashMap<>();
                    linesStream.map(line -> line.split(":"))
                            .forEach(parts -> loadedCart.put(parts[0], Integer.parseInt(parts[1])));
                    cartItems = loadedCart;
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading cart from file: " + e.getMessage());
        }
    }

    private void saveCart() {
        try {
            Path path = Paths.get(CART_FILE);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            // Create a list of strings to write to the file
            List<String> lines = cartItems.entrySet().stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .collect(Collectors.toList());

            Files.write(path, lines, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing cart to file: " + e.getMessage());
        }
    }
}
