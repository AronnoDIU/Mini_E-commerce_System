/**
 * The {@code Item} class represents an item with a unique identifier, name, price, and description.
 * Instances of this class are immutable.
 */
public final class Item {
    private final String itemId;
    private final String name;
    private final double price;
    private final String description;

    // Constructor
    public Item(String itemId, String name, double price, String description) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Overriding toString method for easy display
    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}