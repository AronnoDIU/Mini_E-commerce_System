import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private final List<Product> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            cartItems.add(new Product(product.getName(), product.getCategory(), product.getPrice(), quantity));
            product.decreaseQuantity(quantity);
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}