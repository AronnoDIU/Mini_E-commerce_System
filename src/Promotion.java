import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Promotion {
    private static final Logger logger = Logger.getLogger(Promotion.class.getName());
    private static final String PROMOTIONS_FILE = "promotions.txt";

    private String promotionName;
    private String description;
    private double discount;

    public Promotion(String promotionName, String description, double discount) {
        this.promotionName = promotionName;
        this.description = description;
        this.discount = discount;
        savePromotion();
    }

    public String getPromotionName() {
        return promotionName;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
        savePromotion();
    }

    public void setDescription(String description) {
        this.description = description;
        savePromotion();
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        savePromotion();
    }

    private void savePromotion() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Promotion Name: " + promotionName);
            lines.add("Description: " + description);
            lines.add("Discount: " + discount);
            lines.add(""); // Add a blank line to separate promotions
            Files.write(Path.of(PROMOTIONS_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.severe("Error writing promotion to file: " + e.getMessage());
        }
    }

    public static List<Promotion> readPromotions() {
        try {
            List<Promotion> promotions = new ArrayList<>();
            List<String> lines = Files.readAllLines(Path.of(PROMOTIONS_FILE));

            String currentPromotionName = null;
            String currentDescription = null;
            double currentDiscount = 0;

            for (String line : lines) {
                if (line.startsWith("Promotion Name: ")) {
                    if (currentPromotionName != null) {
                        promotions.add(new Promotion(currentPromotionName, currentDescription, currentDiscount));
                    }
                    currentPromotionName = line.substring("Promotion Name: ".length());
                } else if (line.startsWith("Description: ")) {
                    currentDescription = line.substring("Description: ".length());
                } else if (line.startsWith("Discount: ")) {
                    currentDiscount = Double.parseDouble(line.substring("Discount: ".length()));
                }
            }

            if (currentPromotionName != null) {
                promotions.add(new Promotion(currentPromotionName, currentDescription, currentDiscount));
            }

            return promotions;
        } catch (IOException e) {
            logger.severe("Error reading promotions from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}