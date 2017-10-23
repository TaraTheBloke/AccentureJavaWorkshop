package demos.streams.create;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateDemo {

    public static void main(String[] args) {
        printResults("Stream of strings: ", Stream.of("ab", "cd", "ef", "gh"));

        printResults("IntStream: ", IntStream.of(12, 34, 56, 78, 90));

        printResults("IntStream (exclusive range): ", IntStream.range(20, 30));

        printResults("IntStream (inclusive range): ", IntStream.rangeClosed(20, 30));

        printResults("Randomly generated stream: ", Stream.generate(Math::random).limit(4));

        printResults("Stream from collection: ", Arrays.asList("fred", "wilma", "pebbles").stream());
    }

    private static void printResults(String message, IntStream intStream) {
        printResults(message, intStream.boxed());
    }

    private static void printResults(String message, Stream<?> stream) {
        System.out.printf("%s\n  ", message);
        stream.forEach(o -> { System.out.printf("%s ", o); });
        System.out.println("\n");
    }
}
