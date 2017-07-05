package finish.emphasizer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AsteriskWordEmphasizerTest {

    @Test
    public void shouldEmphasizeWordWithAsterisks() throws Exception {
        assertThat(new AsteriskWordEmphasizer().emphasizeWord("hello"), is("*hello*"));
    }
}
