package finish.refactoring.flights;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

public class TravelAllowanceCalculatorTest {

    private static final String DUBLIN = "Dublin";
    private static final String ISTANBUL = "Istanbul";

    private final FlightConcessionsEngine concessionsEngine = mock(FlightConcessionsEngine.class);
    private final FlightPathDatabase pathDatabase = mock(FlightPathDatabase.class);
    private final TravelAllowanceCalculator calculator = new TravelAllowanceCalculator(concessionsEngine, pathDatabase);

    @Test
    public void shouldCalculateAllowanceForLocalFirstClassFlight() {

        Flight flight = aFirstClassFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(100);
        assertThat(calculator.allowanceFor(flight), is(10.0));
    }

    @Test
    public void shouldCalculateAllowanceForForLocalBusinessClassFlight() {
        Flight flight = aBusinessFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(100);
        assertThat(calculator.allowanceFor(flight), closeTo(30.0, 0.1));
    }

    @Test
    public void shouldCalculateAllowanceForForLocalEconomyFlight() {
        Flight flight = anEconomyFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(100);
        assertThat(calculator.allowanceFor(flight), is(50.0));
    }

    @Test
    public void shouldCalculateAllowanceForForLongFirstClassFlight() {
        Flight flight = aFirstClassFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(210);
        assertThat(calculator.allowanceFor(flight), is(42.0));
    }

    @Test
    public void shouldCalculateAllowanceForLongBusinessClassFlight() {
        Flight flight = aBusinessFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(210);
        assertThat(calculator.allowanceFor(flight), is(84.0));
    }

    @Test
    public void shouldCalculateAllowanceForForLongEconomyFlight() {
        Flight flight = anEconomyFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(210);
        when(pathDatabase.distanceBetween(DUBLIN, ISTANBUL)).thenReturn(210L);
        assertThat(calculator.allowanceFor(flight), is(126.0));
    }

    @Test
    public void shouldCalculateAllowanceForForMultipleLocalFlights() {
        Flight flight1 = aFirstClassFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(100);
        Flight flight2 = aFirstClassFlight()
                            .thatFliesBetween(DUBLIN, ISTANBUL)
                            .andTravels(100);
        assertThat(calculator.allowanceFor(flight1, flight2), is(20.0));
    }

    @Test
    public void shouldAddConcessionBenefitForSingleFlight() {
        Flight flight = anEconomyFlight().thatFliesBetween(DUBLIN, ISTANBUL).andTravels(100);
        when(concessionsEngine.additionalBenefit(1, 50.0)).thenReturn(20.0);
        assertThat(calculator.allowanceFor(flight), is(70.0));
    }

    private class FlightBuilder {

        private final Seating seating;
        private String origin;
        private String destination;

        public FlightBuilder thatFliesBetween(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
            return this;
        }

        public Flight andTravels(long distance) {
            when(pathDatabase.distanceBetween(origin, destination)).thenReturn(distance);
            return new Flight(origin, destination, seating, new Date());
        }

        FlightBuilder(Seating seating) {
            this.seating = seating;
        }
    }

    private FlightBuilder aFirstClassFlight() {
        return new FlightBuilder(Seating.first());
    }

    private FlightBuilder aBusinessFlight() {
        return new FlightBuilder(Seating.business());
    }

    private FlightBuilder anEconomyFlight() {
        return new FlightBuilder(Seating.economy());
    }
}
