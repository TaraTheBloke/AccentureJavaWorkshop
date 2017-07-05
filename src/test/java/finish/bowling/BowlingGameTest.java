package finish.bowling;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BowlingGameTest {

    @Test
    public void shouldScoreAllMisses() throws Exception {
        assertThat(BowlingGame.score(0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0), is(0));
    }

    @Test
    public void shouldScoreAllOpenFrames() throws Exception {
        assertThat(BowlingGame.score(3,6, 6,3, 3,6, 6,3, 6,3, 6,3, 6,3, 6,3, 6,3, 6,3), is(90));
    }

    @Test
    public void shouldScoreAllSpares() throws Exception {
        assertThat(BowlingGame.score(3,7, 3,7, 3,7, 3,7, 3,7, 3,7, 3,7, 3,7, 3,7, 3,7,3), is(130));
    }

    @Test
    public void shouldScoreAllStrikes() throws Exception {
        assertThat(BowlingGame.score(10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,10), is(300));
    }

    @Test
    public void shouldScoreCombinations() throws Exception {
        assertThat(BowlingGame.score(10, 3,6, 5,5, 10, 0,1, 3,2, 7,1, 10, 10, 3,7,10), is(136));
    }
}