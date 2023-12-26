import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Review {
    private static final Logger logger = Logger.getLogger(Review.class.getName());
    private static final String REVIEW_FILE = "reviews.txt";

    private String product;
    private String reviewer;
    private int rating;
    private String comment;

    public Review(String product, String reviewer, int rating, String comment) {
        this.product = product;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static void writeReview(Review review) {
        try {
            String reviewData = review.getProduct() + "," + review.getReviewer() + "," + review.getRating() + "," + review.getComment() + "\n";
            Files.write(Path.of(REVIEW_FILE), reviewData.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing review to file: " + e.getMessage());
        }
    }

    public static List<Review> readReviews() {
        List<Review> reviews = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(REVIEW_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Review review = new Review(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                    reviews.add(review);
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading reviews from file: " + e.getMessage());
        }
        return reviews;
    }
}
