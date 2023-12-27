import java.util.List;

public interface IUserActions {
    // Method to view user profile
    void viewProfile();

    // Method to update user profile
    void updateProfile();

    void placeOrder(List<Product> products);
}