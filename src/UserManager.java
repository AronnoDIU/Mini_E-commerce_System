import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
        initializeUsers();
    }

    private void initializeUsers() {
        // Example Admin
        Admin admin = new Admin("admin", "admin123", "Admin User",
                new ProductCatalog(), new OrderManager());
        users.put(admin.getUsername(), admin);

        // Example Customer
        Customer customer = new Customer("customer", "customer123",
                "Customer User", "123 Main St", "X1L2b@example.com");
        users.put(customer.getUsername(), customer);
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.authenticate(password);
    }
}