package finish.refactoring.flights;

public class Seating {

    private final double allowanceRatio;

    public static Seating first() {
        return new Seating(0.2);
    }

    public static Seating business() {
        return new Seating(0.4);
    }

    public static Seating economy() {
        return new Seating(0.6);
    }

	private Seating(double allowanceRatio) {
        this.allowanceRatio = allowanceRatio;
	}

	double allowanceFor(long distance) {
	    if (isLocalFlight(distance)) {
	        return distance * (allowanceRatio - 0.1);
	    }
	    return distance * allowanceRatio;
    }

    private boolean isLocalFlight(long distance) {
        return distance <= 200;
    }
}
