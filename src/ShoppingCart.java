import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

class ShoppingCart implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<Product, Integer> cart;

    public ShoppingCart() {
        cart = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + quantity);
        } else {
            cart.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            if (quantity >= currentQuantity) {
                cart.remove(product);
            } else {
                cart.put(product, currentQuantity - quantity);
            }
        } else {
            System.out.println("Product not in the cart!");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Shopping Cart:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " - Quantity: " + quantity + " - Total Price: $" + (product.getPrice() * quantity));
            }
        }
    }
}