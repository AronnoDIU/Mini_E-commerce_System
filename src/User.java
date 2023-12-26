import java.io.Serial;

class User implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final ShoppingCart shoppingCart;

    public User(String username, String password, ShoppingCart shoppingCart) {
        this.username = username;
        this.password = password;
        this.shoppingCart = shoppingCart;
    }

    public User(String username) {
        this.username = username;
        this.password = "";
        this.shoppingCart = new ShoppingCart();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.shoppingCart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String toString() {
        return username + " (" + password + "): " + shoppingCart;
    }

    public boolean equals(Object obj) {
        if (obj instanceof User other) {
            return username.equals(other.username) && password.equals(other.password)
                    && shoppingCart.equals(other.shoppingCart);
        } else {
            return false;
        }
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.add(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        shoppingCart.remove(product, quantity);
    }

    public void clearCart() {
        shoppingCart.clear();
    }

    public void checkout() {
        shoppingCart.checkout();
    }

    public void printReceipt() {
        shoppingCart.printReceipt();
    }

    public void printCart() {
        shoppingCart.printCart();
    }

    public void printProducts() {
        shoppingCart.printProducts();
    }
}