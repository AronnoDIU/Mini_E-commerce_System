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

    public User(String userId, String username, String password, String address, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.shoppingCart = new ShoppingCart();
        this.orderManager = new OrderManager();
    }

    public User(String loggedInUser) {
        this.username = loggedInUser;
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
        String newName = userInput.nextLine().trim();
        if (!newName.isEmpty()) {
            this.setName(newName);
        }

        System.out.println("Enter new userId (press Enter to keep current userId): ");
        String newUserId = userInput.nextLine().trim();
        if (!newUserId.isEmpty()) {
            this.setUserId(newUserId);
        }

        System.out.println("Enter new username (press Enter to keep current username): ");
        String newUsername = userInput.nextLine().trim();
        if (!newUsername.isEmpty()) {
            this.setUsername(newUsername);
        }

        System.out.println("Enter new address (press Enter to keep current address): ");
        String newAddress = userInput.nextLine().trim();
        if (!newAddress.isEmpty()) {
            this.setAddress(newAddress);
        }

        System.out.println("Enter new email (press Enter to keep current email): ");
        String newEmail = userInput.nextLine().trim();
        if (!newEmail.isEmpty()) {
            this.setEmail(newEmail);
        }

        System.out.println("Enter new password (press Enter to keep current password): ");
        String newPassword = userInput.nextLine().trim();
        if (!newPassword.isEmpty()) {
            this.setPassword(newPassword);
        }

        System.out.println("Profile updated successfully!");
    }

    public List<Product> viewCart() {
        System.out.println("Shopping Cart Contents:");
        shoppingCart.displayCart();
        System.out.println("Total Price: $" + shoppingCart.calculateTotalPrice());
        return shoppingCart.displayCart();
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.addItem(product, quantity);
    }

    public void removeCartItem(int quantity) {
        shoppingCart.removeFromCart(quantity);
    }

    public void placeOrder(List<Product> cartItems) {
        cartItems = shoppingCart.displayCart();

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Unable to place order.");
            return;
        }

        System.out.println("Total Price: $" + shoppingCart.calculateTotalPrice());
        System.out.println("Order Placed Successfully!");

        Order order = new Order(cartItems, this);
        shoppingCart.clearCart();
    }

    public List<Order> viewOrderHistory() {
        List<Order> orders = orderManager.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println(order);
            }
        }

        return orders;
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
            double total = calculateTotal();
            double discountedTotal = total - (total * discountPercentage / 100);

            if (discountedTotal < 0) {
                System.out.println("Discount cannot result in a negative total. No changes applied.");
            } else {
                shoppingCart.applyDiscount(discountPercentage);
                System.out.println("Discount applied successfully!");
            }
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
