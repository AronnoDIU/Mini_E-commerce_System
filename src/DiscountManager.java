import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountManager {
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
            if (Files.exists(Paths.get(DISCOUNT_FILE))) {
                Map<String, Double> discounts = new HashMap<>();
                Files.lines(Paths.get(DISCOUNT_FILE))
                        .map(line -> line.split(":"))
                        .forEach(parts -> discounts.put(parts[0], Double.parseDouble(parts[1])));
                productDiscounts = discounts;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}