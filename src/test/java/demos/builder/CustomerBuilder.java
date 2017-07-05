package demos.builder;

public class CustomerBuilder {

    private Address address;
    private final String name;

    public static CustomerBuilder aCustomerCalled(String name) {
        return new CustomerBuilder(name);
    }

    private CustomerBuilder(String name) {
        this.name = name;
    }

    public CustomerBuilder with(AddressBuilder builder) {
        this.address = builder.build();
        return this;
    }

    public Customer build() {
        return new Customer(name, address);
    }
}
