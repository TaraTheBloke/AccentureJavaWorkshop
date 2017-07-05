package finish.katas.imperative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCounter {

    public static void main(String[] args) {
        String fileName = promptForFileName();
        while (shouldProcess(fileName)) {
            print(wordCountsIn(fileName));
            fileName = promptForFileName();
        }
    }

    @SuppressWarnings("resource")
    private static String promptForFileName() {
        System.out.print("Enter file name > ");
        return new Scanner(System.in).nextLine();
    }

    private static boolean shouldProcess(String fileName) {
        return !"exit".equalsIgnoreCase(fileName);
    }

    private static Map<String, Integer> wordCountsIn(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            return wordCountsIn(scanner);
        } catch (FileNotFoundException e) {
            return Collections.emptyMap();
        }
    }

    private static Map<String, Integer> wordCountsIn(Scanner scanner) {
        Map<String, Integer> wordCounts = new TreeMap<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            int count = wordCounts.getOrDefault(word, 0);
            wordCounts.put(word, ++count);
        }
        return wordCounts;
    }

    private static void print(Map<String, Integer> wordCounts) {
        for (String word : wordCounts.keySet()) {
            System.out.printf("%s  %s\n", word, wordCounts.get(word));
        }
    }
}
