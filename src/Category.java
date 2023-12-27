import java.io.Serializable;

public enum Category implements Serializable {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    BOOKS_AND_TOYS("Books and Toys"),
    HOME_AND_KITCHEN("Home and Kitchen"),
    HEALTH_AND_BEAUTY("Health and Beauty"),
    BEAUTY("Beauty"),
    FURNITURE("Furniture"),
    GROCERIES("Groceries"),
    BOOKS("Books"),
    SPORTS("Sports");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}