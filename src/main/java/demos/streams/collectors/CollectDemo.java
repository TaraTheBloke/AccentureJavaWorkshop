package demos.streams.collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class CollectDemo {

    public static void main(String[] args) {
        List<String> items = asList("ab", "cde", "fg", "hij", "kl", "mno");

        List<String> results = new ArrayList<>();
        items.stream()
             .filter(s -> s.length() == 3)
             .forEach(results::add);
        results.forEach(System.out::println);

        results = items.stream()
                       .filter(s -> s.length() == 3)
                       .collect(toList());
        results.forEach(System.out::println);
    }
}
