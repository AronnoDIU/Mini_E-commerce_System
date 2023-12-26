import java.io.Serial;

class Product implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String category;
    private final double price;
    private int stock;

    public Product(String name, String category, double price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity > 0 && quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            stock += amount; // quantity = quantity + amount;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public String toString() {
        return name + ": $" + price + " (" + stock + ")";
    }
}