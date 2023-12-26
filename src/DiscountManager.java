import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DiscountManager {
    private static final Logger logger = Logger.getLogger(DiscountManager.class.getName());
    private static final String DISCOUNT_FILE = "discounts.txt";

    private Map<String, Double> productDiscounts;

    public DiscountManager() {
        this.productDiscounts = new HashMap<>();
        loadDiscounts();
    }

    public double getDiscount(String productName) {
        return productDiscounts.getOrDefault(productName, 0.0);
    }

    public void setDiscount(String productName, double discount) {
        productDiscounts.put(productName, discount);
        saveDiscounts();
    }

    private void loadDiscounts() {
        try {
            Path path = Paths.get(DISCOUNT_FILE);
            if (Files.exists(path)) {
                // Use try-with-resources to ensure the stream is closed
                try (var linesStream = Files.lines(path)) {
                    Map<String, Double> discounts = new HashMap<>();
                    linesStream.map(line -> line.split(":"))
                            .forEach(parts -> discounts.put(parts[0], Double.parseDouble(parts[1])));
                    productDiscounts = discounts;
                }
            }
        } catch (IOException e) {
            logger.severe("Error reading discounts from file: " + e.getMessage());
        }
    }

    private void saveDiscounts() {
        try {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, Double> entry : productDiscounts.entrySet()) {
                lines.add(entry.getKey() + ":" + entry.getValue());
            }
            Files.write(Paths.get(DISCOUNT_FILE), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing discounts to file: " + e.getMessage());
        }
    }
}