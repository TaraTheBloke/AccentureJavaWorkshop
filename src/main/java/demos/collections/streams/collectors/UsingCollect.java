package demos.collections.streams.collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UsingCollect {

    public static void main(String[] args) {
        List<String> items = asList("ab", "cde", "fg", "hij", "kl", "mno");
        List<String> results1 = new ArrayList<>();

        demo1(items.stream(), results1);
        List<String> results2 = demo2(items.stream());

        // just for demo
        results1.forEach(System.out::println);
        results2.forEach(System.out::println);
    }

    private static void demo1(Stream<String> stream, List<String> results1) {
        stream.filter(s -> s.length() == 3).forEach(results1::add);
    }

    private static List<String> demo2(Stream<String> stream) {
        return stream.filter(s -> s.length() == 3).collect(toList());
    }
}
