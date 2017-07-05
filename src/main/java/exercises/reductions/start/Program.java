package exercises.reductions.start;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Program {
	public static void main(String[] args) {
		sumViaReduce(data());
		countViaReduce(data());
		averageViaReduce(data());
		lastViaReduce(data());
		penultimateViaReduce(data());
		reverseViaReduce(data());
	}

	private static IntStream data() {
		return IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	}

	private static void sumViaReduce(IntStream data) {
	    int result = 0;
		System.out.println("Sum of elements is " + result);
	}

	private static void countViaReduce(IntStream data) {
	    int result = 0;
		System.out.println("Count of elements is " + result);
	}

	private static void averageViaReduce(IntStream data) {
	    double result = 0;
		System.out.println("Average of elements is " + result);
	}

	private static void lastViaReduce(IntStream data) {
		int result = data.reduce((a,  b) -> b).getAsInt();
		System.out.println("The last elements is " + result);
	}

	private static void penultimateViaReduce(IntStream data) {
	    Stream<Integer> stream = data.mapToObj(i -> new Integer(i));
		int[] result = stream.reduce(new int[] { 0, 0 },
		                         (a, b) -> new int[] { a[1], b },
		                         (a, b) -> a);
		System.out.println("The penultimate elements is " + result[0]);
	}

	private static void reverseViaReduce(IntStream data) {
        Stream<Integer> stream = data.mapToObj(i -> new Integer(i));
	    ArrayList<Integer> result = stream.reduce(new ArrayList<Integer>(),
	                                              (a, b) -> { a.add(0, b); return a; },
	                                              (a, b) -> a);
		System.out.println("The list in reverse is: " + result);
	}
}
