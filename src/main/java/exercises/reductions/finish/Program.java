package exercises.reductions.finish;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.summarizingInt;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.stream.Stream;

class Program {
    public static void main(String[] args) {
        sumViaReduce(data());
        sumViaCollect(data());
        productViaReduce(data());
        countViaReduce(data());
        countViaCollect(data());
        averageViaReduce(data());
        averageViaCollect(data());
        lastViaReduce(data());
        penultimateViaReduce(data());
        reverseViaReduce(data());
    }

    private static Stream<Integer> data() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    private static void sumViaReduce(Stream<Integer> data) {
        //int result = data.reduce((a,b) -> a + b).orElse(0);
        int result = data.reduce(Integer::sum).orElse(0);
        System.out.println("Sum of elements is " + result);
    }

    private static void sumViaCollect(Stream<Integer> data) {
        long result = data.collect(summarizingInt(x -> x)).getSum();
        System.out.println("Sum of elements via collect is " + result);
    }


    private static void productViaReduce(Stream<Integer> data) {
        int result = data.reduce((a,b) -> a * b).orElse(0);
        System.out.println("Product of elements is " + result);
    }

    private static void countViaReduce(Stream<Integer> data) {
        int result = data.reduce(0, (sum,item) -> sum + 1);
        System.out.println("Count of elements is " + result);
    }

    private static void countViaCollect(Stream<Integer> data) {
        long result = data.collect(counting());
        System.out.println("Count of elements via collect is " + result);
    }

    private static void averageViaReduce(Stream<Integer> data) {
        class Pair {
            public double sum;
            public int count;
        }
        BiFunction<Pair,Integer,Pair> operator = (pair,item) -> {
            pair.sum += item;
            pair.count += 1;
            return pair;
        };
        Pair pair = data.reduce(new Pair(), operator, (a,b) -> null);
        double average = pair.sum / pair.count;
        System.out.println("Average of elements is " + average);
    }

    private static void averageViaCollect(Stream<Integer> data) {
        double result = data.collect(averagingDouble(x -> x));
        System.out.println("Average of elements via collect is " + result);
    }

    private static void lastViaReduce(Stream<Integer> data) {
        int result = data.reduce((a,b) -> b)
                         .orElseThrow(IllegalStateException::new);
        System.out.println("The last element is " + result);
    }

    private static void penultimateViaReduce(Stream<Integer> data) {
        int [] lastTwoItems = data.reduce(new int[] {0,0},
                                              (a,b) -> new int[]{a[1],b},
                                              (a,b) -> null);
        int penultimateItem = lastTwoItems[0];
        System.out.println("The penultimate elements is " + penultimateItem);
    }

    private static void reverseViaReduce(Stream<Integer> data) {
        ArrayList<Integer> results = new ArrayList<>();
        data.reduce(results,
                    (newList,item) -> {
                            newList.add(0,item);
                            return newList;
                        },
                    (a,b) -> null);
        System.out.println("The list in reverse is:");
        for(Integer value : results) {
            System.out.printf("\t%d\n", value);
        }
    }
}
