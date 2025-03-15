// src/Rating.java
public class Rating {
    private final String username;
    private final int rating;

    public Rating(String username, int rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "username='" + username + '\'' +
                ", rating=" + rating +
                '}';
    }
}