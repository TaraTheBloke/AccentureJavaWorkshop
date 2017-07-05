package demos.collections.streams.extra;

import static demos.collections.streams.extra.Utils.buildStaff;
import static demos.collections.streams.extra.Utils.printResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        demoFlatMap();
        demoCollecting();
        demoGeneration();
    }

    private static void demoCollecting() {
        List<Employee> staff = buildStaff();

        Map<Department, List<Employee>> results1;
        Map<Integer, List<Employee>> results2;

        results1 = staff.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        results2 = staff.stream().collect(Collectors.groupingBy(Employee::getSalaryBand));

        System.out.println("Results of first grouping:");
        for (Department d : results1.keySet()) {
            System.out.printf("\tIn %s: ", d);
            for (Employee e : results1.get(d)) {
                System.out.printf("%s ", e);
            }
            System.out.println();
        }
        System.out.println("Results of second grouping:");
        for (int salaryBand : results2.keySet()) {
            System.out.printf("\tBand %d: ", salaryBand);
            for (Employee e : results2.get(salaryBand)) {
                System.out.printf("%s ", e);
            }
            System.out.println();
        }
    }

    private static void demoGeneration() {
        Stream<Double> input = Stream.generate(Math::random).limit(5);
        printResults("Generated stream content:", input);
    }

    private static void demoFlatMap() {
        List<Employee> staff = buildStaff();
        Stream<String> results = staff.stream().flatMap(e -> e.getAddresses().stream()).map(Address::toString);
        printResults("Results from mappings:", results);
    }
}
