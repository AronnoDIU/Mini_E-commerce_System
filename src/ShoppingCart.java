import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> items;
    private double totalPrice;

    // Co
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(Product product, int quantity) {
        items.add(new Product(product, quantity));
        totalPrice += product.getPrice() * quantity;
        System.out.println(quantity + " " + product.getName() + " added to the cart.");
    }

    // Remove a product from the cart
    public void removeFromCart(int productId) {
        for (Product product : items) {
            if (product.getProductId() == productId) {
                items.remove(product);
                totalPrice -= product.getPrice();
                System.out.println(product.getName() + " removed from the cart.");
                return;
            }
        }
        System.out.println("Product not found in the cart. Unable to remove.");
    }

    // Clear the cart
    public void clearCart() {
        items.clear();
        totalPrice = 0.0;
        System.out.println("Cart cleared.");
    }

    // Calculate the total price of items in the cart
    public double calculateTotalPrice() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }


    // Display the items in the cart
    public List<Product> displayCart() {
        System.out.println("Shopping Cart:");
        for (Product product : items) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
        System.out.println("Total Price: $" + calculateTotalPrice());
        return new ArrayList<>(items);
    }

    // Apply a discount to the cart
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            double discountAmount = (discountPercentage / 100) * totalPrice;
            totalPrice -= discountAmount;
            System.out.println("Discount of $" + discountAmount
                    + " applied. New total: $" + calculateTotalPrice());
        } else {
            System.out.println("Invalid discount percentage. Please provide a percentage between 0 and 100.");
        }
    }

    public void addItem(Product product) {
        items.add(product);
        totalPrice += product.getPrice();
    }
}
