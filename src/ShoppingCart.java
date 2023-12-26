import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

class ShoppingCart implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Product> cartItems;

    public ShoppingCart(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            cartItems.add(new Product(product.getName(),
                    product.getCategory(), product.getPrice(), quantity));
            product.decreaseQuantity(quantity);
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems.clear();
        this.cartItems.addAll(cartItems);
    }

    public void removeFromCart(Product product) {
        for (Product item : cartItems) {
            if (item.getName().equals(product.getName())) {
                item.increaseQuantity(product.getQuantity());
            }
        }
        cartItems.remove(product);
    }

    public void removeFromCart(String name) {
        for (Product product : cartItems) {
            if (product.getName().equals(name)) {
                cartItems.remove(product);
                break;
            }
        }
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

    public void removeFromCart(Product product, int quantity) {
        for (Product item : cartItems) {
            if (item.getName().equals(product.getName())) {
                if (item.getQuantity() >= quantity) {
                    item.decreaseQuantity(quantity);
                    product.increaseQuantity(quantity);
                } else {
                    System.out.println("Not enough stock for " + product.getName());
                }
            }
        }
    }

    public void printCartItems() {
        for (Product product : cartItems) {
            System.out.println(product);
        }
    }

    public void printCartItems(String category) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(double minPrice, double maxPrice) {
        for (Product product : cartItems) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3, double minPrice3, double maxPrice3) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3) && product.getPrice() >= minPrice3 && product.getPrice() <= maxPrice3) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3, double minPrice3, double maxPrice3, int minQuantity3, int maxQuantity3) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice
                    && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity
                    && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3) && product.getPrice() >= minPrice3 && product.getPrice() <= maxPrice3 && product.getQuantity() >= minQuantity3 && product.getQuantity() <= maxQuantity3) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3, double minPrice3, double maxPrice3, int minQuantity3, int maxQuantity3, String name3) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3) && product.getPrice() >= minPrice3 && product.getPrice() <= maxPrice3 && product.getQuantity() >= minQuantity3 && product.getQuantity() <= maxQuantity3 && product.getName().equals(name3)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3, double minPrice3, double maxPrice3, int minQuantity3, int maxQuantity3, String name3, String category4) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3) && product.getPrice() >= minPrice3 && product.getPrice() <= maxPrice3 && product.getQuantity() >= minQuantity3 && product.getQuantity() <= maxQuantity3 && product.getName().equals(name3) && product.getCategory().equals(category4)) {
                System.out.println(product);
            }
        }
    }

    public void printCartItems(String category, double minPrice, double maxPrice, int minQuantity, int maxQuantity, String name, String category2, double minPrice2, double maxPrice2, int minQuantity2, int maxQuantity2, String name2, String category3, double minPrice3, double maxPrice3, int minQuantity3, int maxQuantity3, String name3, String category4, double minPrice4, double maxPrice4) {
        for (Product product : cartItems) {
            if (product.getCategory().equals(category) && product.getPrice() >= minPrice && product.getPrice() <= maxPrice && product.getQuantity() >= minQuantity && product.getQuantity() <= maxQuantity && product.getName().equals(name) && product.getCategory().equals(category2) && product.getPrice() >= minPrice2 && product.getPrice() <= maxPrice2 && product.getQuantity() >= minQuantity2 && product.getQuantity() <= maxQuantity2 && product.getName().equals(name2) && product.getCategory().equals(category3) && product.getPrice() >= minPrice3 && product.getPrice() <= maxPrice3 && product.getQuantity() >= minQuantity3 && product.getQuantity() <= maxQuantity3 && product.getName().equals(name3) && product.getCategory().equals(category4) && product.getPrice() >= minPrice4 && product.getPrice() <= maxPrice4) {
                System.out.println(product);
            }
        }
    }

    public void add(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            cartItems.add(new Product(product.getName(),
                    product.getCategory(), product.getPrice(), quantity));
            product.decreaseQuantity(quantity);
        } else {
            System.out.println("Not enough stock for " + product.getName());
        }
    }

    public void remove(Product product, int quantity) {
        for (Product item : cartItems) {
            if (item.getName().equals(product.getName())) {
                if (item.getQuantity() >= quantity) {
                    item.decreaseQuantity(quantity);
                    product.increaseQuantity(quantity);
                } else {
                    System.out.println("Not enough stock for " + product.getName());
                }
            }
        }
    }

    public void clear() {
        cartItems.clear();
    }

    public void checkout() {
        for (Product product : cartItems) {
            product.increaseQuantity(product.getQuantity());
        }
        cartItems.clear();
    }

    public void printReceipt() {
        for (Product product : cartItems) {
            System.out.println(product);
        }
        System.out.println("Total: $" + calculateTotal());
    }

    public void printCart() {
        for (Product product : cartItems) {
            System.out.println(product);
        }
    }

    public void printProducts() {
        for (Product product : cartItems) {
            System.out.println(product);
        }
    }
}