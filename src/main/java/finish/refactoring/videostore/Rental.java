package finish.refactoring.videostore;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    double amount() {
        return movie.amountFor(daysRented);
    }

    int frequentRenterPoints() {
        return movie.frequentRenterPointFor(daysRented);
    }
}
