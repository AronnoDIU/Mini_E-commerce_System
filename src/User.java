import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private String userId;
    private String username;
    private String password;
    private String address;
    private String email;
    ShoppingCart shoppingCart;
    private final OrderManager orderManager;

    // Constructors
    public User(String name, String userId, String username,
                String password, String address, String email) {
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.shoppingCart = new ShoppingCart();
        this.orderManager = new OrderManager();
    }

    // Constructor for Admin
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.shoppingCart = new ShoppingCart();
        this.orderManager = new OrderManager();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void viewProfile() {
        System.out.println("Name: " + getName());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
        System.out.println("Address: " + getAddress());
        System.out.println("Email: " + getEmail());
        System.out.println("Profile viewed successfully!");
    }
    public void updateProfile() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter new name (press Enter to keep current name): ");
        String newName = userInput.nextLine();
        if (!newName.isEmpty()) {
            this.setName(newName);
        }

        System.out.println("Enter new userId (press Enter to keep current userId): ");
        String newUserId = userInput.nextLine();
        if (!newUserId.isEmpty()) {
            this.setUserId(newUserId);
        }

        System.out.println("Enter new username (press Enter to keep current username): ");
        String newUsername = userInput.nextLine();
        if (!newUsername.isEmpty()) {
            this.setUsername(newUsername);
        }

        System.out.println("Enter new address (press Enter to keep current address): ");
        String newAddress = userInput.nextLine();
        if (!newAddress.isEmpty()) {
            this.setAddress(newAddress);
        }

        System.out.println("Enter new email (press Enter to keep current email): ");
        String newEmail = userInput.nextLine();
        if (!newEmail.isEmpty()) {
            this.setEmail(newEmail);
        }

        System.out.println("Enter new password (press Enter to keep current password): ");
        String newPassword = userInput.nextLine();
        if (!newPassword.isEmpty()) {
            this.setPassword(newPassword);
        }

        System.out.println("Profile updated successfully!");
    }

    public List<Product> viewCart() {
        System.out.println("Shopping Cart Contents:");
        shoppingCart.displayItems();
        return null;
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.addItem(product, quantity);
    }

    public void removeCartItem(Product product, int quantity) {
        shoppingCart.removeItem(product, quantity);
    }

    public void placeOrder() {
        List<Product> cartItems = shoppingCart.getCartItems();
        // Implement order placement logic, e.g., calling a method in the OrderHandler class
        OrderHandler.placeOrder(this, cartItems);
        shoppingCart.clearCart();
    }

    public List<Order> viewOrderHistory() {
        return orderManager.getAllOrders();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", shoppingCart=" + shoppingCart +
                ", orderManager=" + orderManager +
                '}';
    }

    public void applyDiscount(double discountPercentage) {
        if (shoppingCart != null) {
            shoppingCart.applyDiscount(discountPercentage);
            System.out.println("Discount applied successfully!");
        } else {
            System.out.println("Shopping cart is null. Cannot apply discount.");
        }
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public double calculateTotal() {
        double total = 0.0;

        for (Product product : shoppingCart.displayCart()) {
            total += product.getPrice();
        }

        return total;
    }
}
