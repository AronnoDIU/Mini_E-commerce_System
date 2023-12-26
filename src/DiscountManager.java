class DiscountManager {

    public static void applyDiscount() {
        System.out.println("Applying discount...");
    }

    public static void applyDiscount(String discountCode) {
        System.out.println("Applying discount " + discountCode + "...");
    }

    public static void applyDiscount(String discountCode, String productName) {
        System.out.println("Applying discount " + discountCode + " to " + productName + "...");
    }

    public static void applyDiscount(String discountCode, String productName, String categoryName) {
        System.out.println("Applying discount " + discountCode + " to " + productName + " in " + categoryName + "...");
    }
}
