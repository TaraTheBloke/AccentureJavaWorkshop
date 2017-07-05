package finish.poker.functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class HandReader {

    public static List<Hand> handsIn(String fileName) {
        try {
            return Files.lines(Paths.get(fileName))
                        .map(Hand::new)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

}
