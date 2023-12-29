/**
 * The {@code Item} class represents an item with a unique identifier, name, price, and description.
 * Instances of this class are immutable.
 */
public record Item(String itemId, String name, double price, String description) {

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