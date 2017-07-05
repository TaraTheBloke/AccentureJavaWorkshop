package exercises.refactoring.flights;

import java.util.Date;

public class Flight {
	public Flight(String originAirport, String destinationAirport, Seating seating, Date date) {
		super();
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.seating = seating;
		this.date = date;
	}
	public String getOriginAirport() {
		return originAirport;
	}
	public String getDestinationAirport() {
		return destinationAirport;
	}
	public Seating getSeating() {
		return seating;
	}
	public Date getDate() {
		return date;
	}
	private final String originAirport;
	private final String destinationAirport;
	private final Seating seating;
	private final Date date;
}
