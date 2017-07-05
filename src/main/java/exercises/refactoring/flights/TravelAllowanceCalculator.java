package exercises.refactoring.flights;

public class TravelAllowanceCalculator {
	private static final long MAX_DISTANCE_OF_LOCAL_FLIGHT = 200;

	public TravelAllowanceCalculator(FlightConcessionsEngine concessionsEngine,
						  			 FlightPathDatabase pathDatabase) {
		super();
		this.concessionsEngine = concessionsEngine;
		this.pathDatabase = pathDatabase;
	}
	public double calculateAllowance(Flight ... flights) {
		double allowance = 0;

		for(Flight flight : flights) {
			long distance = pathDatabase.findFlightPath(flight.getOriginAirport(),
														flight.getDestinationAirport());
			switch(flight.getSeating()) {
	        case FIRST:
	            if(flightIsLocal(distance)) {
	                allowance += distance * 0.10;
	            } else {
	                allowance += distance * 0.20;
	            }
	            break;
	        case BUSINESS:
	            if(flightIsLocal(distance)) {
	                allowance += distance * 0.30;
	            } else {
	                allowance += distance * 0.40;
	            }
	            break;
	        case ECONOMY:
	            if(flightIsLocal(distance)) {
	                allowance += distance * 0.50;
	            } else {
	                allowance += distance * 0.60;
	            }
	            break;
		     }
		}
		if(concessionsEngine.additionalBenefitApplies(flights.length,allowance)) {
			allowance += concessionsEngine.calculateBenefit(flights.length,allowance);
		}
		return allowance;
	}
	private boolean flightIsLocal(long distance) {
		return distance <= MAX_DISTANCE_OF_LOCAL_FLIGHT;
	}
	private final FlightPathDatabase pathDatabase;
	private final FlightConcessionsEngine concessionsEngine;
}
