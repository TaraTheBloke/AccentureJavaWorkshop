package finish.poker.imperative;

import java.util.List;
import java.util.Scanner;

public class PokerFace {

    public static void main(String[] args) {
        new PokerFace().nameHands();
    }

    private void nameHands() {
        String fileName = promptForFileName();
        print(handsIn(fileName));
    }

    @SuppressWarnings("resource")
    private String promptForFileName() {
        System.out.println("name of file > ");
        return new Scanner(System.in).nextLine();
    }

    private List<Hand> handsIn(String fileName) {
        return HandReader.handsIn(fileName);
    }

    private void print(List<Hand> hands) {
        for (Hand hand : hands) {
            System.out.printf("%s => %s\n", hand.cards(), hand.name());
        }
    }
}
