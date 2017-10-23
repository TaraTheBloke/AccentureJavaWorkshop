package demos.streams.interop;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class InteropDemo {

    public static void main(String[] args) {

        int[] numbers = { 10, 25, 40, 55, 70, 85 };
        DoubleStream ds = Arrays.stream(numbers).mapToDouble(i -> i * 2.0);
        ds.forEach(d -> System.out.println(d));

        System.out.println("----");

        Stream<String> ss = Arrays.stream(numbers).mapToObj(String::valueOf);
        ss.filter(s -> s.endsWith("5"))
          .forEach(System.out::println);
    }
}
