package finish.poker.functional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HandTest {

    @Test
    public void shouldRetainSuppliedCards() throws Exception {
        assertThat(new Hand("TH JH QH KH AH").cards(), is("TH JH QH KH AH"));
    }

    @Test
    public void shouldNameRoyalFlush() throws Exception {
        assertThat(new Hand("TH JH QH KH AH").name(), is("royal flush"));
        assertThat(new Hand("TS JS QS KS AS").name(), is("royal flush"));
    }

    @Test
    public void shouldNameStraightFlush() throws Exception {
        assertThat(new Hand("9H TH JH QH KH").name(), is("straight flush"));
        assertThat(new Hand("AH 4H 3H 2H 5H").name(), is("straight flush"));
    }

    @Test
    public void shouldNameFourOfAKind() throws Exception {
        assertThat(new Hand("9H 9S 9D KH 9C").name(), is("four of a kind"));
        assertThat(new Hand("AS 9S AD AH AC").name(), is("four of a kind"));
    }

    @Test
    public void shouldNameFullHouse() throws Exception {
        assertThat(new Hand("3D 6C 3H 6D 3C").name(), is("full house"));
    }

    @Test
    public void shouldNameFlush() throws Exception {
        assertThat(new Hand("AS 6S 3S KS 8S").name(), is("flush"));
    }

    @Test
    public void shouldNameStraight() throws Exception {
        assertThat(new Hand("2D 3C 4H 5S 6C").name(), is("straight"));
    }

    @Test
    public void shouldNameThreeOfAKind() throws Exception {
        assertThat(new Hand("2D 3C 4H 3S 3D").name(), is("three of a kind"));
    }

    @Test
    public void shouldNameTwoPair() throws Exception {
        assertThat(new Hand("KD 4C KC 9S 4D").name(), is("two pair"));
    }

    @Test
    public void shouldNameSinglePair() throws Exception {
        assertThat(new Hand("KD 4C KC 9S 8D").name(), is("one pair"));
    }

    @Test
    public void shouldNameHighCard() throws Exception {
        assertThat(new Hand("KD 4C AC 9S 8D").name(), is("high card"));
    }
}
