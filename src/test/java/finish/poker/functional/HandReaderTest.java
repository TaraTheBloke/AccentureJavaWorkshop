package finish.poker.functional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class HandReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private String fileName;

    @Before
    public void setup() throws Exception {
        Path file = Paths.get("hands.txt");
        Collection<String> lines = Arrays.asList("3H JS 3C 7C 5D",
                                                 "JH 2C JD 2H 4C",
                                                 "9H 9D 3S 9S 9C",
                                                 "9C 3H 9S 9H 3S");
        Files.write(file, lines);
        fileName = file.getFileName().toString();
    }

    @Test
    public void shouldReadHandFromFile() throws Exception {
        List<Hand> hands = HandReader.handsIn(fileName);
        assertThat(hands.size(), is(4));
    }

    @Test
    public void shouldNotReadHandsWhenFileDoesNotExist() throws Exception {
        List<Hand> hands = HandReader.handsIn("/tmp/hgjhgjgjg");
        assertThat(hands.size(), is(0));
    }
}
