package exercises.refactoring.flights;

public interface FlightPathDatabase {
	long findFlightPath(String originAirport, String destinationAirport);
}
