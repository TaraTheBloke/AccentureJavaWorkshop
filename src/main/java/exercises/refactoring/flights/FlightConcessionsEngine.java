package exercises.refactoring.flights;

public interface FlightConcessionsEngine {
    boolean additionalBenefitApplies(int numFlights, double totalCost);
    double calculateBenefit(int numFlights, double totalCost);
}
