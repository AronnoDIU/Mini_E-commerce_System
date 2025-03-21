/**
 * The IAdminActions interface defines actions that can be performed by an admin user.
 */
public interface IAdminActions {

    // Remove a product from the catalog
    void removeProduct(Integer productId);

    /**
     * View statistics related to products in the system.
     */
    void viewProductStats();

    
    void manageUsers();
}
