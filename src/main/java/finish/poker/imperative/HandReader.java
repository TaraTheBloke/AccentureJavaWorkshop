package finish.poker.imperative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HandReader {

    public static List<Hand> handsIn(String fileName) {
        List<Hand> hands = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                hands.add(new Hand(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            // file does not exist
        }
        return hands;
    }

}
