import java.io.Serial;

class Product implements Comparable<Product>, java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String category;
    private final double price;
    private int quantity;

    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product[] getProducts() {
        return new Product[0];
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

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount; // quantity = quantity - amount;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            quantity += amount; // quantity = quantity + amount;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public String toString() {
        return name + " (" + category + "): $" + price + " x " + quantity;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Product other) {
            return name.equals(other.name) && category.equals(other.category)
                    && price == other.price && quantity == other.quantity;
        }
        return false;
    }

    public int hashCode() {
        return name.hashCode() + category.hashCode() + (int) price + quantity;
    }

    public int compareTo(Product other) {
        return name.compareTo(other.name);

        // if (name.compareTo(other.name) < 0) {
        //     return -1;
        // } else if (name.compareTo(other.name) > 0) {
        //     return 1;
    }

    public void print() {
        System.out.println(this);
    }
}