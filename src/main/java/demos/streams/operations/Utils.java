package demos.streams.operations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public class Utils {

    public static Stream<Character> toStream(String input) {
        Character[] output = new Character[input.length()];
        for (int i = 0; i < input.length(); i++) {
            output[i] = input.charAt(i);
        }
        return Arrays.stream(output);
    }

    public static void printResults(String msg, BaseStream<?, ?> results) {
        System.out.printf("%s\n\t", msg);
        Iterator<?> iter = results.iterator();
        while (iter.hasNext()) {
            System.out.printf("%s ", iter.next());
        }
        System.out.println();
    }
}
