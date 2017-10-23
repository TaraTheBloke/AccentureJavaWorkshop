package demos.streams.collectors;

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

public class CollectorsDemo {

    public static void main(String[] args) {
        System.out.println("Count is:\t" + alphabet().collect(counting()));
        System.out.println("Joining gives:\t" + alphabet().collect(joining()));
        System.out.println("Average gives:\t" + alphabet().collect(averagingDouble(String::length)));
        System.out.println("Summing gives:\t" + alphabet().collect(summingInt(String::length)));

        Map<Integer, List<String>> groups = alphabet().collect(groupingBy(String::length));
        System.out.println("Grouping gives:");
        groups.entrySet().stream().forEach(entry -> {
            System.out.printf("\t%d indexes ", entry.getKey());
            entry.getValue().stream()
                 .forEach(value -> System.out.printf(" %s", value));
            System.out.println();
        });

        List<String> list1 = alphabet().collect(toList());
        List<String> list2 = alphabet().collect(toCollection(LinkedList::new));
        Set<String> set1 = alphabet().collect(toSet());
        Set<String> set2 = alphabet().collect(toCollection(TreeSet::new));

        System.out.println(list1.getClass().getName());
        System.out.println(list2.getClass().getName());
        System.out.println(set1.getClass().getName());
        System.out.println(set2.getClass().getName());
    }

    private static Stream<String> alphabet() {
        return Stream.of("ab", "cde", "fghi", "jk", "lmn", "opqr", "st", "uvw", "xyz");
    }
}