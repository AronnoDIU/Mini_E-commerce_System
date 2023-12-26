import java.io.Serial;

class Review implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String description;
    private final int rating;

    public Review(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getRating() {
        return this.rating;
    }

    public String toString() {
        return this.name + ": " + this.description + " (" + this.rating + ")";
    }
}
