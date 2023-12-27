import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Product> items;
    private double totalPrice;

    // Constructor
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Add an item to the cart
    public void addItem(Product product) {
        items.add(product);
        System.out.println(product.getName() + " added to the cart.");
    }

    public void removeItem(Product product) {
        items.remove(product);
        System.out.println(product.getName() + " removed from the cart.");
    }

    // Clear the cart
    public void clearCart() {
        items.clear();
        totalPrice = 0.0;
    }

    // Calculate the total price of items in the cart
    public double calculateTotal() {
        return totalPrice;
    }

    // Display the items in the cart
    public List<Product> displayCart() {
        System.out.println("Shopping Cart:");
        for (Product product : items) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
        System.out.println("Total Price: $" + totalPrice);
        return items;
    }

    // Apply a discount to the cart
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            double discountAmount = (discountPercentage / 100) * totalPrice;
            totalPrice -= discountAmount;
            System.out.println("Discount of $" + discountAmount + " applied. New total: $" + totalPrice);
        } else {
            System.out.println("Invalid discount percentage. Please provide a percentage between 0 and 100.");
        }
    }
}