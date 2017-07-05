package demos.builder;


/**
 * Represents a subscriber.
 */
public final class Customer {

    private final String name;
    private final Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Returns customer name.
     */
    public String name() {
        return name;
    }

    /**
     * Returns subscriber address
     */
    public Address address() {
        return address;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Customer[name=")
                .append(name)
                .append(", address=")
                .append(address)
                .append("]")
                .toString();
    }
}