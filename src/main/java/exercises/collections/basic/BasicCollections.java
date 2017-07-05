package exercises.collections.basic;

//Uncomment the code below and then in 'main' try to
//  print out all the numbers, which are greater than
//  or equal to 0.5, formatted according to 'df'

/*import java.text.DecimalFormat;
import java.util.stream.Stream;

public class BasicCollections {
    public static void main(String [] args) {
        Stream<Stream<Double>> input = buildInput();
        final DecimalFormat df = new DecimalFormat(".00");
    }

    private static Stream<Stream<Double>> buildInput() {
        return Stream.generate(() -> Stream.generate(Math::random).limit(10))
                     .limit(10);
    }
}
*/