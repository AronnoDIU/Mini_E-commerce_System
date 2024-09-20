import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
        initializeUsers();
    }

    private void initializeUsers() {
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

    public void manageUsers() {
        if (ECommerceSystem.currentUser instanceof Admin) {

            while (true) {
                System.out.println("User Management Menu");
                System.out.println("1. Add User");
                System.out.println("2. Remove User");
                System.out.println("3. Update User");
                System.out.println("4. Exit User Management");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1: // For Add Use
                        addUser();
                        break;
                    case 2: // For Remove User
                        removeUser();
                        break;
                    case 3: // For Update User
                        updateUser();
                        break;
                    case 4: // For Exit User Management
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Access denied. Only admins can manage users.");
        }
    }

    private void addUser() {
        System.out.println("Enter user details:");

        // Collect user details from admin
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Create a new customer
        User newUser = new Customer(username, password, name, address, email);
        users.put(username, newUser);
        System.out.println("User added successfully.");
    }

    private void removeUser() {
        System.out.print("Enter username to remove: ");
        String usernameToRemove = scanner.nextLine();

        // Remove the user if it exists
        if (users.containsKey(usernameToRemove)) {
            users.remove(usernameToRemove);
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUser() {
        System.out.print("Enter username to update: ");
        String usernameToUpdate = scanner.nextLine();

        if (users.containsKey(usernameToUpdate)) {
            User userToUpdate = users.get(usernameToUpdate);

            // Collect updated user details from admin
            System.out.print("Updated Name (press Enter to keep current name): ");
            String updatedName = scanner.nextLine();
            if (!updatedName.isEmpty()) {
                userToUpdate.setName(updatedName);
            }

            System.out.print("Updated Password (press Enter to keep current password): ");
            String updatedPassword = scanner.nextLine();
            if (!updatedPassword.isEmpty()) {
                userToUpdate.setPassword(updatedPassword);
            }

            System.out.print("Updated Address (press Enter to keep current address): ");
            String updatedAddress = scanner.nextLine();
            if (!updatedAddress.isEmpty()) {
                userToUpdate.setAddress(updatedAddress);
            }

            System.out.print("Updated Email (press Enter to keep current email): ");
            String updatedEmail = scanner.nextLine();
            if (!updatedEmail.isEmpty()) {
                userToUpdate.setEmail(updatedEmail);
            }

            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
}
