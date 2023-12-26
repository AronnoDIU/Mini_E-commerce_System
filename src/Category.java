import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class Category {
    private static final Logger logger = Logger.getLogger(Category.class.getName());
    private static final String CATEGORY_FILE = "categories.txt";

    private final String name;
    private final String description;
    private final List<Product> products;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        saveCategory();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        saveCategory();
    }

    private void saveCategory() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("Name:" + name);
            lines.add("Description:" + description);
            lines.add("Products:");
            for (Product product : products) {
                lines.add(product.getName());
            }
            Files.write(Paths.get(getCategoryFileName()), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.severe("Error writing category to file: " + e.getMessage());
        }
    }

    private String getCategoryFileName() {
        return name.toLowerCase() + ".txt";
    }

    public static Category loadCategory(String categoryName) {
        Category category = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(categoryName.toLowerCase() + ".txt"));
            String categoryDescription = lines.get(1).substring("Description:".length());
            category = new Category(categoryName, categoryDescription);
            for (int i = 3; i < lines.size(); i++) {
                String productName = lines.get(i);
                Product product = Product.loadProduct(productName);
                category.addProduct(product);
            }
        } catch (IOException e) {
            logger.severe("Error reading category from file: " + e.getMessage());
        }
        return category;
    }
}