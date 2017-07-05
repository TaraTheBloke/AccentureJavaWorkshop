package exercises.refactoring.videostore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void statementForRegularMovie() {
        Customer customer = new Customer("Sallie");
        Movie movie1 = new Movie("Gone with the Wind", Movie.REGULAR);
        Rental rental1 = new Rental(movie1, 3);
        customer.addRental(rental1);
        String expected = "Rental Record for Sallie\n" +
                          "\tGone with the Wind\t3.5\n" +
                          "Amount owed is 3.5\n" +
                          "You earned 1 frequent renter points";
        String statement = customer.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForNewReleaseMovie() {
        Customer customer = new Customer("Sallie");
        Movie movie1 = new Movie("Star Wars", Movie.NEW_RELEASE);
        Rental rental1 = new Rental(movie1, 3);
        customer.addRental(rental1);
        String expected = "Rental Record for Sallie\n" +
                          "\tStar Wars\t9.0\n" +
                          "Amount owed is 9.0\n" +
                          "You earned 2 frequent renter points";
        String statement = customer.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForChildrensMovie() {
        Customer customer = new Customer("Sallie");
        Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
        Rental rental1 = new Rental(movie1, 3);
        customer.addRental(rental1);
        String expected = "Rental Record for Sallie\n" +
                          "\tMadagascar\t1.5\n" +
                          "Amount owed is 1.5\n" +
                          "You earned 1 frequent renter points";
        String statement = customer.statement();
        assertEquals(expected, statement);
    }

    @Test
    public void statementForManyMovies() {
        Customer customer1 = new Customer("David");
        Movie movie1 = new Movie("Madagascar", Movie.CHILDRENS);
        Rental rental1 = new Rental(movie1, 6);
        Movie movie2 = new Movie("Star Wars", Movie.NEW_RELEASE);
        Rental rental2 = new Rental(movie2, 2);
        Movie movie3 = new Movie("Gone with the Wind", Movie.REGULAR);
        Rental rental3 = new Rental(movie3, 8);
        customer1.addRental(rental1);
        customer1.addRental(rental2);
        customer1.addRental(rental3);
        String expected = "Rental Record for David\n" +
                          "\tMadagascar\t6.0\n" +
                          "\tStar Wars\t6.0\n" +
                          "\tGone with the Wind\t11.0\n" +
                          "Amount owed is 23.0\n" +
                          "You earned 4 frequent renter points";
        String statement = customer1.statement();
        assertEquals(expected, statement);
    }
}
