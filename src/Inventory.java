import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private static final String INVENTORY_FILE = "inventory.txt";

    private Map<String, Integer> productQuantities;

    public Inventory() {
        this.productQuantities = new HashMap<>();
        loadInventory();
    }

    public int getQuantity(String productName) {
        return productQuantities.getOrDefault(productName, 0);
    }

    public void updateQuantity(String productName, int quantity) {
        productQuantities.put(productName, quantity);
        saveInventory();
    }

    private void loadInventory() {
        try {
            if (Files.exists(Path.of(INVENTORY_FILE))) {
                Map<String, Integer> inventory = new HashMap<>();
                Files.lines(Path.of(INVENTORY_FILE))
                        .map(line -> line.split(":"))
                        .forEach(parts -> inventory.put(parts[0], Integer.parseInt(parts[1])));
                productQuantities = inventory;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveInventory() {
        try {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
                lines.add(entry.getKey() + ":" + entry.getValue());
            }
            Files.write(Path.of(INVENTORY_FILE), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
