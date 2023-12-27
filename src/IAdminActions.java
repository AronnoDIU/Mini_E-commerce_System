/**
 * The IAdminActions interface defines actions that can be performed by an admin user.
 */
public interface IAdminActions {

    /**
     * View statistics related to products in the system.
     */
    void viewProductStats();

    /**
     * Manage users, including tasks such as creating, updating, or deleting user accounts.
     */
    void manageUsers();
}