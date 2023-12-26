class Order {
    private static int orderCounter = 1;

    private final int orderId;
    private final Customer customer;
    private final ShoppingCart cart;

    public Order(Customer customer, ShoppingCart cart) {
        this.orderId = orderCounter++;
        this.customer = customer;
        this.cart = cart;
    }

    public void processOrder() {
        System.out.println("Processing Order #" + orderId);
        System.out.println("Customer: " + customer.getUsername());
        cart.viewCart();
    }
}