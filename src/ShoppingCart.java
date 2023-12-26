import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

class ShoppingCart implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<Product, Integer> cartItems;

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            cartItems.put(product, cartItems.get(product) + quantity);
        } else {
            cartItems.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (quantity >= currentQuantity) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, currentQuantity - quantity);
            }
        } else {
            System.out.println("Product not in the cart!");
        }
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " - Quantity: " + quantity + " - Total Price: $" + (product.getPrice() * quantity));
            }
        }
    }
}