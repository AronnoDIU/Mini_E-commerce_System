import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;

public class FileHandler {
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    final String filePath;
    static final String delimiter = ",";  // Add delimiter declaration

    // Constructor
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Read content from a file
    public String readFile() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Write content to a file
    public void writeFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Write to file successful.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    // Read entities from a file using a generic method
    private <T> List<T> readEntities(Function<BufferedReader, T> entityReader) throws IOException {
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entities.add(entityReader.apply(new BufferedReader(new StringReader(line))));
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
            throw e;
        }
        return entities;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> readUserCredentials() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userCredentials.dat"))) {
            // Read the object from the file
            Object obj = ois.readObject();

            if (obj instanceof Map) {
                return (Map<String, String>) obj;
            } else {
                System.out.println("Invalid format of user credentials file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
            System.out.println("Error reading from the file: " + e.getMessage());
        }

        // Return an empty map if something goes wrong
        return new HashMap<>();
    }

    public ArrayList<Order> readOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] orderDetails = line.split(",");
                String orderId = orderDetails[0];
                String username = orderDetails[1];
                String products = orderDetails[2];
                Date date = dateFormat.parse(orderDetails[3]);
                Date time = timeFormat.parse(orderDetails[4]);
                Date dateTime = combineDateAndTime(date, time);
                Order order = new Order(orderId, username, parseProducts(products), dateTime);
                orders.add(order);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        return orders;
    }

    // Helper method to parse products
    private List<Product> parseProducts(String products) {
        List<Product> productList = new ArrayList<>();
        String[] productDetails = products.split(";");
        for (String productDetail : productDetails) {
            String[] details = productDetail.split(":");
            Integer productId = Integer.parseInt(details[0]);
            String name = details[1];
            String description = details[2];
            double price = Double.parseDouble(details[3]);
            Category category = Category.valueOf(details[4]);
            int stockQuantity = Integer.parseInt(details[5]);
            Product product = new Product(productId, name, description, price, category, stockQuantity);
            productList.add(product);
        }
        return productList;
    }

    // Helper method to combine date and time
    private Date combineDateAndTime(Date date, Date time) {
        // Convert Date to LocalDateTime
        LocalDateTime dateToLocalDateTime =
                date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();

        // Convert Date to LocalDateTime
        LocalDateTime timeToLocalDateTime =
                time.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();

        // Combine date and time
        LocalDateTime combinedDateTime = LocalDateTime.of(
                dateToLocalDateTime.toLocalDate(),
                timeToLocalDateTime.toLocalTime()
        );

        // Convert LocalDateTime to Date
        return Date.from(combinedDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
    }

    public void writeUserCredentials(String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(string);
            System.out.println("Write to file successful.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
