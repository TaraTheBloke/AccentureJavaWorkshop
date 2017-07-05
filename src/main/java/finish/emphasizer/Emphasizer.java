package finish.emphasizer;

import java.util.StringTokenizer;

public class Emphasizer {

    public static String emphasize(String words, WordEmphasizer wordEmphasizer) {
        StringBuilder result = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(words, " ", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isDelimitor(token)) {
                result.append(token);
            } else {
                result.append(wordEmphasizer.emphasizeWord(token));
            }
        }
        return result.toString();
    }

    private static boolean isDelimitor(String token) {
        return token.trim().isEmpty();
    }
}
