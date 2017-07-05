package finish.ports;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PortRangeTest {

    private final PortRange range = new PortRange(1, 10);

    @Test
    public void shouldAcquireFirstAvailablePort() throws Exception {
        assertThat(range.acquirePort(), is(1));
    }

    @Test
    public void shouldAcquirePortsSequentially() throws Exception {
        for (int i = 1; i <= 10; i++) {
            assertThat(range.acquirePort(), is(i));
        }
    }

    @Test
    public void shouldNotAcquirePortWhenAllAssigned() throws Exception {
        for (int i = 1; i <= 10; i++) {
            range.acquirePort();
        }
        assertThat(range.acquirePort(), is(-1));
    }

    @Test
    public void shouldFreePort() throws Exception {
        for (int i = 1; i <= 5; i++) {
            range.acquirePort();
        }
        range.freePort(3);
        assertThat(range.acquirePort(), is(3));
        assertThat(range.acquirePort(), is(6));
    }
}
