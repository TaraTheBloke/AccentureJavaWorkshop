package demos.builder;



/**
 * Represents a subscriber address
 */
public final class Address {

    private final String street;
    private final String city;
    private final String county;
    private final String country;
    private final String postCode;

    /**
     * Constructs an address
     */
    public Address(String street, String city, String county, String country, String postCode) {
        this.street = street;
        this.city = city;
        this.county = county;
        this.country = country;
        this.postCode = postCode;
    }

    public String street() {
        return street;
    }

    public String city() {
        return city;
    }

    public String county() {
        return county;
    }

    public String country() {
        return country;
    }

    public String postCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Address[street=")
                .append(street)
                .append(", city=")
                .append(city)
                .append(", county=")
                .append(county)
                .append(", country=")
                .append(country)
                .append(", postCode=")
                .append(postCode)
                .append("]")
                .toString();
    }
}