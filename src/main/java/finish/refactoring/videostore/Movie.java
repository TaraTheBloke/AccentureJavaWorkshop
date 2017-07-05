package finish.refactoring.videostore;

/**
 *  Takes a different direction than Martin Fowler's original in
 *  that the 'price code' metaphor is removed completely. Instead
 *  it is replaced by a MovieType enum.  MovieType provides type
 *  specific implementations for calculating the frequent renter
 *  points and rental amount, enabling the removal of the original
 *  switch logic entirely.  The resulting code is more verbose but
 *  more object oriented / polymorphic.
 *
 *  Also it is no longer possible to change the price code (or movie
 *  type as it now is) of the movie.  Once constructed it really
 *  should be immutable as changing the price code (or movie type)
 *  doesn't make sense - after a movie is associated with a rental,
 *  the movie type cannot change as otherwise its rental price will
 *  be affected for the associated customer.  If it is no longer a
 *  'new release' and is to be marked a 'regular' release, for
 *  example, then we construct a new movie object when it is rented
 *  next.
 */
public class Movie {

    public static enum MovieType {
        CHILDRENS {
            @Override
            double amountFor(int daysRented) {
                double amount = 1.5;
                if (daysRented > 3) {
                    amount += (daysRented - 3) * 1.5;
                }
                return amount;
            }

            @Override
            int frequentRenterPointsFor(int daysRented) {
                return 1;
            }
        },

        REGULAR  {
            @Override
            double amountFor(int daysRented) {
                double amount = 2;
                if (daysRented > 2) {
                    amount += (daysRented - 2) * 1.5;
                }
                return amount;
            }

            @Override
            int frequentRenterPointsFor(int daysRented) {
                return 1;
            }
        },

        NEW_RELEASE  {
            @Override
            double amountFor(int daysRented) {
                return daysRented * 3;
            }

            @Override
            int frequentRenterPointsFor(int daysRented) {
                return (daysRented > 1) ? 2 : 1;
            }
        };

        abstract double amountFor(int daysRented);
        abstract int frequentRenterPointsFor(int daysRented);
    }


    private final String title;
    private MovieType movieType;

    public Movie(String title, MovieType movieType) {
        this.title = title;
        this.movieType = movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public String getTitle() {
        return title;
    }

    double amountFor(int daysRented) {
        return movieType.amountFor(daysRented);
    }

    int frequentRenterPointFor(int daysRented) {
        return movieType.frequentRenterPointsFor(daysRented);
    }
}
