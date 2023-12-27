import java.util.List;
import java.util.Scanner;

public class User implements IUserActions {

    String name;
    private String userId;
    private String username;
    private String password;
    private String address;
    private String email;
    private ShoppingCart shoppingCart;
    private OrderManager orderManager;

    // Constructor
    public User(String name, String userId, String username, String password, String address, String email) {
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.shoppingCart = new ShoppingCart();
        this.orderManager = new OrderManager();
    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public User(String userId, String username, String password, String address, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
    }

    public User(String loggedInUser) {
        this.username = loggedInUser;
    }

    // Getters
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

    // toString method for easy representation
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void viewProfile() {
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);
    }

    @Override
    public void updateProfile() {
        System.out.println("Enter new name: ");
        name = ECommerceSystem.scanner.nextLine();
        System.out.println("Enter new username: ");
        username = ECommerceSystem.scanner.nextLine();
        System.out.println("Enter new address: ");
        address = ECommerceSystem.scanner.nextLine();
        System.out.println("Enter new email: ");
        email = ECommerceSystem.scanner.nextLine();
        System.out.println("Enter new password: ");
        password = ECommerceSystem.scanner.nextLine();
        System.out.println("Profile updated successfully!");
    }

    @Override
    public void placeOrder(List<Product> products) {
        if (shoppingCart != null) {
               // Create a new order
//            Order newOrder = new Order(products);
            // Clear the shopping cart after placing the order
            shoppingCart.clearCart();
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Shopping cart is null. Cannot place an order.");
        }
    }

    @Override
    public void viewCart() {
        if (shoppingCart != null) {
            shoppingCart.viewCart();
        } else {
            System.out.println("Shopping cart is null. Cannot view cart.");
        }
    }

    @Override
    public List<Order> viewOrderHistory() {
        return orderManager.getAllOrders();
    }

    public void addToCart(Product product) {
        shoppingCart.addItem(product);
        System.out.println("Product added to the shopping cart.");
    }
}
