package demos.streams.extra;

public class Address {

    private int streetNumber;
    private String streetName;
    private String city;

    public Address(int streetNumber, String streetName, String city) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        String msg = "%d %s %s";
        return String.format(msg, streetNumber, streetName, city);
    }
}
