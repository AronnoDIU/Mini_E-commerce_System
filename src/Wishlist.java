// src/Wishlist.java
import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private final List<Product> wishlistItems;

    public Wishlist() {
        this.wishlistItems = new ArrayList<>();
    }

    public void addItem(Product product) {
        wishlistItems.add(product);
        System.out.println("Product added to wishlist: " + product.getName());
    }

    public void removeItem(Product product) {
        wishlistItems.remove(product);
        System.out.println("Product removed from wishlist: " + product.getName());
    }

    public List<Product> viewWishlist() {
        return new ArrayList<>(wishlistItems);
    }

    public void clearWishlist() {
        wishlistItems.clear();
        System.out.println("Wishlist cleared.");
    }
}
