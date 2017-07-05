package finish.katas.functional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class WordCounter {

    public static void main(String[] args) {
        new WordCounter().countWord();
    }

    private final Scanner stdin = new Scanner(System.in);

    public void countWord() {
        String fileName;
        while ((!isExit(fileName = promptForFileName()))) {
            print(countOf(wordsIn(fileName)));
        }
    }

    private boolean isExit(String fileName) {
        return "exit".equalsIgnoreCase(fileName);
    }

    private String promptForFileName() {
        System.out.print("Enter file nane > ");
        return stdin.nextLine();
    }

    private List<String> wordsIn(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                        .flatMap(line -> Stream.of(line.split(" +")))
                        .collect(toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private Map<String, Long> countOf(List<String> words) {
        return words.stream().collect(groupingBy(identity(), counting()));
    }

    private void print(Map<String, Long> wordCounts) {
        wordCounts.entrySet().stream().forEach(e -> {
            System.out.printf("%1$10s   %2$d\n", e.getKey(), e.getValue());
        });
    }
}