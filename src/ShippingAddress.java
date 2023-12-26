import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ShippingAddress {
    private static final String ADDRESS_FILE = "shipping_addresses.txt";

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public ShippingAddress(String name, String streetAddress, String city, String state, String zipCode, String country) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Methods for File Operations

    public static void writeShippingAddress(ShippingAddress address) {
        try {
            String addressData = address.getName() + "," + address.getStreetAddress() + "," +
                    address.getCity() + "," + address.getState() + "," +
                    address.getZipCode() + "," + address.getCountry() + "\n";
            Files.write(Path.of(ADDRESS_FILE), addressData.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
        }
    }

    public static List<ShippingAddress> readShippingAddresses() {
        List<ShippingAddress> addresses = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(ADDRESS_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    ShippingAddress address = new ShippingAddress(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    addresses.add(address);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle this more gracefully in a production scenario
        }
        return addresses;
    }
}
