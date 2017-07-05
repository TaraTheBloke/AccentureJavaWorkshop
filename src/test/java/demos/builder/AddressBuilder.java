package demos.builder;

public final class AddressBuilder {

    private String street;
    private String city;
    private String county;
    private String country;
    private String postCode;

    public static AddressBuilder anAddress() {
        return new AddressBuilder();
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder withCounty(String county) {
        this.county = county;
        return this;
    }

    public AddressBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public Address build() {
        return new Address(street, city, county, country, postCode);
    }

    private AddressBuilder() {
        // private
    }
}
