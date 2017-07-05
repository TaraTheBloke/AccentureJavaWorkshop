package demos.collections.streams.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Program {
    private static final Path PATH = FileSystems.getDefault().getPath("input");

    public static void main(String[] args) throws IOException {
        try (Stream<Path> folderContent = Files.list(PATH)) {
            System.out.println("Here are the folders contents");
            folderContent.forEach(Program::printPath);
        }
        System.out.println("\nHere are the first five lines (or less) from each file");
        Files.list(PATH)
             .filter(path -> path.toFile().isFile())
             .flatMap(path -> readLines(path).limit(5))
             .forEach(System.out::println);
    }

    private static Stream<String> readLines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPath(Path path) {
        File file = path.toFile();
        if (file.isDirectory()) {
            System.out.println("\tNested folder: " + path);
        } else if (file.isFile()) {
            System.out.println("\tThe file: " + path);
        }
    }
}
