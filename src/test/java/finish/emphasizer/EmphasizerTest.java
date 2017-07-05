package finish.emphasizer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class EmphasizerTest {

    /*
     * Contrasting multiple approaches to unit testing the emphasizer.
     * First approach - to use a mock - is most verbose.
     */

    @Test
    public void shouldEmphasizeSingleWordUsingMock() throws Exception {
        WordEmphasizer wordEmphasizer = mock(WordEmphasizer.class);
        when(wordEmphasizer.emphasizeWord("hello"))
            .thenReturn("HELLO");
        assertThat(Emphasizer.emphasize("hello", wordEmphasizer), is("HELLO"));
    }

    @Test
    public void shouldEmphasizeMultipleWordsUsingMock() throws Exception {
        WordEmphasizer wordEmphasizer = mock(WordEmphasizer.class);
        when(wordEmphasizer.emphasizeWord("hello")).thenReturn("HELLO");
        when(wordEmphasizer.emphasizeWord("there")).thenReturn("THERE");
        when(wordEmphasizer.emphasizeWord("world")).thenReturn("WORLD");

        assertThat(Emphasizer.emphasize("hello  there world", wordEmphasizer), is("HELLO  THERE WORLD"));
    }

    /*
     * Second approach, uses a real word emphasizer.  Results in
     * shorter tests but more complex test up.
     */

    private final WordEmphasizer wordEmphasizer = new WordEmphasizer() {
        @Override
        public String emphasizeWord(String word) {
            return word.toUpperCase();
        }
    };

    @Test
    public void shouldEmphasizeSingleWordUsingRealStrategy() throws Exception {
        assertThat(Emphasizer.emphasize("hello", wordEmphasizer), is("HELLO"));
    }

    @Test
    public void shouldEmphasizeMultipleUsingRealStrategy() throws Exception {
        assertThat(Emphasizer.emphasize("hello there world", wordEmphasizer), is("HELLO THERE WORLD"));
    }

    /*
     * Alternative - decompose emphasis logic into helper method to
     * help shorten test code.  Can result in less fixture which can
     * be a good thing - less to confuse the reader with - but can
     * result in more complex helper logic.
     */

    @Test
    public void shouldEmphasizeSingleWord() throws Exception {
        assertThat(emphasize("hello"), is("HELLO"));
    }

    @Test
    public void shouldEmphasizeMultipleWords() throws Exception {
        assertThat(emphasize("hello there world"), is("HELLO THERE WORLD"));
    }

    private String emphasize(String sentence) {
        return Emphasizer.emphasize(sentence, word -> word.toUpperCase());
    }
}