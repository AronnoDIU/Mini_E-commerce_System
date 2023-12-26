class Order {
    private final String name;
    private int quantity;
    private final double price;

    public Order(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public String toString() {
        return name + ": $" + price + " x " + quantity;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Order other) {
            return name.equals(other.name) && price == other.price
                    && quantity == other.quantity;
        } else {
            return false;
        }
    }

    public double getTotal() {
        return price * quantity;
    }

    public void increaseQuantity(int amount) {
        if (amount > 0) {
            quantity += amount; // quantity = quantity + amount;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void decreaseQuantity(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount; // quantity = quantity - amount;
        } else {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void setQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            setQuantity(quantityInt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void increaseQuantity(String amount) {
        try {
            int amountInt = Integer.parseInt(amount);
            increaseQuantity(amountInt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please try again.");
        }
    }

    public void decreaseQuantity(String amount) {
        try {
            int amountInt = Integer.parseInt(amount);
            decreaseQuantity(amountInt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please try again.");
        }
    }
}
