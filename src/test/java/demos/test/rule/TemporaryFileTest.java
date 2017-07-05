package demos.test.rule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TemporaryFileTest {

    @Rule
    public TemporaryFolder directory = new TemporaryFolder();

    private Path path;

    @Before
    public void before() throws Exception {
        System.out.println("temporary dir=" + directory.getRoot());
        path = Paths.get(directory.newFile("tmp.txt").getPath());
        Files.write(path, Arrays.asList("1", "2", "3"), StandardOpenOption.APPEND);
    }

    @Test
    public void isUsingTemporaryDirectory() throws Exception {
        System.out.println("temporary dir=" + directory.getRoot());
        Files.lines(path)
             .forEach(System.out::println);
    }
}
