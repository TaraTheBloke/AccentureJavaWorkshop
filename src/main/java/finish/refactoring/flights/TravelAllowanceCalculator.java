package finish.refactoring.flights;

public class TravelAllowanceCalculator {

    private final FlightPathDatabase pathDatabase;

    private final FlightConcessionsEngine concessionsEngine;

    public TravelAllowanceCalculator(FlightConcessionsEngine concessionsEngine,
                                     FlightPathDatabase pathDatabase) {
        this.concessionsEngine = concessionsEngine;
        this.pathDatabase = pathDatabase;
    }

    public double allowanceFor(Flight... flights) {
        double allowance = 0;
        for (Flight flight : flights) {
            long distance = pathDatabase.distanceBetween(flight.getOriginAirport(), flight.getDestinationAirport());
            allowance += flight.allowanceFor(distance);
        }
        allowance += concessionsEngine.additionalBenefit(flights.length, allowance);
        return allowance;
    }
}
