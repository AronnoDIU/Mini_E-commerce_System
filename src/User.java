import java.util.List;
import java.util.Scanner;

public class User implements IUserActions {

    private String name;
    private String userId;
    private String username;
    private String password;
    private String address;
    private String email;
    private ShoppingCart shoppingCart;
    private OrderManager orderManager;

    // Constructors

    // Full constructor
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

    // Minimal constructor
    public User(String userId, String username, String password, String address, String email) {
        this(null, userId, username, password, address, email);
    }

    // For login purposes
    public User(String loggedInUser) {
        this.username = loggedInUser;
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

    @Override
    public void viewProfile() {
        System.out.println("User Profile:");
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);
        // Do not print password for security reasons
    }

    @Override
    public void updateProfile() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter new name (press Enter to keep current name): ");
        String newName = userInput.nextLine();
        if (!newName.isEmpty()) {
            this.name = newName;
        }

        System.out.println("Enter new username (press Enter to keep current username): ");
        String newUsername = userInput.nextLine();
        if (!newUsername.isEmpty()) {
            this.username = newUsername;
        }

        System.out.println("Enter new address (press Enter to keep current address): ");
        String newAddress = userInput.nextLine();
        if (!newAddress.isEmpty()) {
            this.address = newAddress;
        }

        System.out.println("Enter new email (press Enter to keep current email): ");
        String newEmail = userInput.nextLine();
        if (!newEmail.isEmpty()) {
            this.email = newEmail;
        }

        System.out.println("Enter new password (press Enter to keep current password): ");
        String newPassword = userInput.nextLine();
        if (!newPassword.isEmpty()) {
            this.password = newPassword;
        }

        System.out.println("Profile updated successfully!");
    }

    @Override
    public void placeOrder(List<Product> products) {
        if (shoppingCart != null) {
            // Assuming an Admin instance is needed to create an order
            Admin admin = new Admin(this.name, this.userId,
                    this.username, this.password, this.address, this.email);
            Order newOrder = new Order(products, admin);
            orderManager.createOrder(newOrder);
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
        if (shoppingCart != null) {
            shoppingCart.addItem(product);
            System.out.println("Product added to the shopping cart.");
        } else {
            System.out.println("Shopping cart is null. Cannot add to cart.");
        }
    }
}

//    // toString method for easy representation
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId='" + userId + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
//
//
//    @Override
//    public void placeOrder(List<Product> products) {
//        if (shoppingCart != null) {
//            Order newOrder = new Order(products, (Admin) this);
//            orderManager.createOrder(newOrder);
//            shoppingCart.clearCart();
//            System.out.println("Order placed successfully!");
//        } else {
//            System.out.println("Shopping cart is null. Cannot place an order.");
//        }
//    }
//
//    @Override
//    public void viewCart() {
//        if (shoppingCart != null) {
//            shoppingCart.viewCart();
//        } else {
//            System.out.println("Shopping cart is null. Cannot view cart.");
//        }
//    }
//
//    @Override
//    public List<Order> viewOrderHistory() {
//        return orderManager.getAllOrders();
//    }
//
//    public void addToCart(Product product) {
//        shoppingCart.addItem(product);
//        System.out.println("Product added to the shopping cart.");
//    }
//}
