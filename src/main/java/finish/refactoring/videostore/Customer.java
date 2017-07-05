package finish.refactoring.videostore;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String name;

    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";
        for (Rental rental : rentals) {
            result += "\t" + rental.getMovie().getTitle() + "\t" + rental.amount() + "\n";
        }
        result +=  "Amount owed is " + totalAmount() + "\n";
        result += "You earned " + frequentRenterPoints() + " frequent renter points";
        return result;
    }

    private int frequentRenterPoints() {
        return rentals.stream()
                      .mapToInt(Rental::frequentRenterPoints)
                      .sum();
    }

    private double totalAmount() {
        return rentals.stream()
                      .mapToDouble(Rental::amount)
                      .sum();
    }
}