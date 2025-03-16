// src/Discount.java
public class Discount {
    private final double percentage;
    private final String description;

    public Discount(double percentage, String description) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        this.percentage = percentage;
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "percentage=" + percentage +
                ", description='" + description + '\'' +
                '}';
    }
}