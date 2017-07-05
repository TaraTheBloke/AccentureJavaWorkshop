package demos.collections.streams.collectors;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class BuiltInCollectors {

    public static void main(String[] args) {
        System.out.println("Count is:\t" + sampleStream().collect(counting()));
        System.out.println("Joining gives:\t" + sampleStream().collect(joining()));
        System.out.println("Average gives:\t" + sampleStream().collect(averagingDouble(String::length)));
        System.out.println("Summing gives:\t" + sampleStream().collect(summingInt(String::length)));

        Map<Integer, List<String>> groups = sampleStream().collect(groupingBy(String::length));
        System.out.println("Grouping gives:");
        groups.entrySet().stream().forEach(entry -> {
            System.out.printf("\t%d indexes ", entry.getKey());
            entry.getValue().stream()
                 .forEach(value -> System.out.printf(" %s", value));
            System.out.println();
        });

        List<String> list1 = sampleStream().collect(toList());
        Set<String> set1 = sampleStream().collect(toSet());
        List<String> list2 = sampleStream().collect(toCollection(LinkedList::new));
        Set<String> set2 = sampleStream().collect(toCollection(TreeSet::new));

        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        System.out.println(set1.getClass().getName());
        System.out.println(set2.getClass().getName());
    }

    private static Stream<String> sampleStream() {
        return Stream.of("ab", "cde", "fghi", "jk", "lmn", "opqr", "st", "uvw", "xyz");
    }
}
