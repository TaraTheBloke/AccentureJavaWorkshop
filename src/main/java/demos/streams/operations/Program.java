package demos.streams.operations;

import static demos.streams.operations.Utils.printResults;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        List<String> data = Arrays.asList("ab", "cde", "fg", "hij", "kl", "mno", "pq", "rst", "uv", "wxyz");

        demoInternalIteration(data);
        demoTesting(data);
        demoFiltering(data);
        demoMapping(data);
        demoFlatMapping(data);
        demoReducing(data);
        demoSingleUse(data);
    }

    private static void demoInternalIteration(List<String> data) {
        System.out.printf("Internal iteration produced:\n\t");
        data.stream().forEach(s -> System.out.printf("%s ", s));
        System.out.println();
    }

    private static void demoTesting(List<String> data) {
        System.out.println("Testing produced:");
        System.out.println("\t" + data.stream().findAny().orElse("Empty!"));
        if (data.stream().allMatch(s -> s.length() > 1)) {
            System.out.println("\tAll strings longer than 1");
        }
        if (data.stream().anyMatch(s -> s.length() > 3)) {
            System.out.println("\tAt least one string longer than 3");
        }
        if (data.stream().noneMatch(s -> s.length() > 4)) {
            System.out.println("\tNo strings longer than 4");
        }
    }

    private static void demoFiltering(List<String> data) {
        Stream<String> results = data.stream().filter(s -> s.length() == 3);
        printResults("Filtering produced:", results);
    }

    private static void demoMapping(List<String> data) {
        Stream<Integer> results1 = data.stream().map(s -> s.length());
        printResults("Mapping produced:", results1);

        IntStream results2 = data.stream().mapToInt(s -> s.length());
        printResults("Mapping to int produced:", results2);

        LongStream results3 = data.stream().mapToLong(s -> s.length());
        printResults("Mapping to long produced:", results3);

        DoubleStream results4 = data.stream().mapToDouble(s -> s.length() * 1.0);
        printResults("Mapping to double produced:", results4);
    }

    private static void demoFlatMapping(List<String> data) {
        Stream<Character> results1 = data.stream().flatMap(Utils::toStream);
        printResults("First flat mapping produced:", results1);

        IntStream results2 = data.stream().flatMapToInt(String::chars);
        printResults("Second flat mapping produced:", results2);

        Stream<Character> results3 = data.stream()
                                         .flatMapToInt(String::chars)
                                         .mapToObj(i -> Character.valueOf((char) i));
        printResults("Third flat mapping produced:", results3);
    }

    private static void demoReducing(List<String> data) {
        // reducing to the same type (requires only an accumulator)
        Optional<String> result1 = data.stream().reduce((s1, s2) -> s1 + "-" + s2);
        System.out.printf("First reduction produced:\n\t%s\n", result1.orElse("nothing"));

        // reducing to a different type (requires accumulator and combiner):
        StringBuilder result2 = data.stream()
                                    .reduce(new StringBuilder(), (sb, s) -> sb.insert(0, s), (sb1, sb2) -> sb1);
        System.out.printf("Second reduction produced:\n\t%s\n", result2);

        // reducing to a different type.
        int result3 = data.stream().reduce(0, (total, s) -> total + s.codePointAt(0), (a, b) -> a + b);
        System.out.printf("Third reduction produced:\n\t%s\n", result3);

        // reducing to a different type.
        int result4 = data.stream().reduce(0, (totalLength, s) -> totalLength + s.length(), (l1, l2) -> l1 + l2);
        System.out.printf("Combined length of all strings:\n\t%s\n", result4);
    }

    private static void demoSingleUse(List<String> data) {
        Stream<String> stream = data.stream();
        System.out.println("First iteration of stream...");

        stream.forEach(System.out::println);
        System.out.println("Second iteration of stream...");

        try {
            stream.forEach(System.out::println);
        } catch (IllegalStateException ex) {
            System.out.println("Whoops should not have done that...");
        }
    }
}
