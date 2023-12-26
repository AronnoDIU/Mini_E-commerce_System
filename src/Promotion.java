import java.io.Serial;

class Promotion implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String code;
    private final String description;
    private double discount;

    public Promotion(String code, String description, double discount) {
        this.code = code;
        this.description = description;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount > 0) {
            this.discount = discount;
        } else {
            System.out.println("Invalid discount. Please try again.");
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Promotion other) {
            return code.equals(other.code) && description.equals(other.description)
                    && discount == other.discount;
        } else {
            return false;
        }
    }

    public String toString() {
        return code + ": " + description + " (" + discount + "%)";
    }
}
