package demos.streams.create;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateDemo {

    public static void main(String[] args) {
        print("Stream of strings: ", Stream.of("ab", "cd", "ef", "gh"));

        print("IntStream: ", IntStream.of(12, 34, 56, 78, 90));

        print("IntStream (exclusive range): ", IntStream.range(20, 30));

        print("IntStream (inclusive range): ", IntStream.rangeClosed(20, 30));

        print("Randomly generated stream: ", Stream.generate(Math::random).limit(4));

        print("Stream from collection: ", Arrays.asList("fred", "wilma", "pebbles").stream());
    }

    private static void print(String message, IntStream intStream) {
        print(message, intStream.boxed());
    }

    private static void print(String message, Stream<?> stream) {
        System.out.printf("%s\n  ", message);
        stream.forEach(o -> { System.out.printf("%s ", o); });
        System.out.println("\n");
    }
}
