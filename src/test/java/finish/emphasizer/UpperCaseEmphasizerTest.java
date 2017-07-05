package finish.emphasizer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UpperCaseEmphasizerTest {

    @Test
    public void shouldConvertWordToUpperCase() throws Exception {
        assertThat(new UpperCaseEmphasizer().emphasizeWord("hello"), is("HELLO"));
    }
}
