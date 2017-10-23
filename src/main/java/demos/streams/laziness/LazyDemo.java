package demos.streams.laziness;

import java.io.PrintStream;
import java.util.stream.IntStream;

public class LazyDemo {

    public static void main(String[] args) {
        PrintStream out = System.out;

        out.println("Demo begins");
        IntStream input = IntStream.of(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        out.println("Input has been built");

        IntStream results = input.filter(x -> x % 2 == 0)
                                 .peek(x -> out.printf("\t%d survived filter\n", x))
                                 .map(x -> x * 2);
        out.println("Results have been built");

        results.forEach(item -> out.printf("Processed result %d\n", item));
    }
}