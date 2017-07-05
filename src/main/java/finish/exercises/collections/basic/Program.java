package finish.exercises.collections.basic;

import java.text.DecimalFormat;
import java.util.stream.Stream;

public class Program {

    public static void main(String [] args) {
        solutionOne();
        spacer();
        solutionTwo();
        spacer();
        solutionThree();
    }

	private static void spacer() {
		System.out.println("--------------------------");
	}

	private static void solutionOne() {
		Stream<Stream<Double>> input = buildInput();
        final DecimalFormat df = new DecimalFormat(".00");

        input.flatMap(x -> x)
             .filter(x -> x >= 0.5)
             .map(df::format)
             .forEach(System.out::println);
	}

	private static void solutionTwo() {
		Stream<Stream<Double>> input = buildInput();
        final DecimalFormat df = new DecimalFormat(".00");

        input.flatMap(x -> x.filter(y -> y >= 0.5))
             .map(df::format)
             .forEach(System.out::println);
	}

	private static void solutionThree() {
		Stream<Stream<Double>> input = buildInput();
        final DecimalFormat df = new DecimalFormat(".00");
        input.flatMap(x -> x.filter(y -> y >= 0.5).map(df::format))
             .forEach(System.out::println);
	}

    private static Stream<Stream<Double>> buildInput() {
        return Stream.generate(() -> Stream.generate(Math::random).limit(10))
                     .limit(10);
    }

}
