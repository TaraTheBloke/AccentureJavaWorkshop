package finish.ports;

import java.util.BitSet;

public class PortRange {

    private final int minimumPortNumber;
    private final int maximumPortNumber;
    private final BitSet ports = new BitSet();

    public PortRange(int minimumPortNumber, int maximumPortNumber) {
        this.minimumPortNumber = minimumPortNumber;
        this.maximumPortNumber = maximumPortNumber;
    }

    public int acquirePort() {
        for (int portNumber = minimumPortNumber; portNumber <= maximumPortNumber; portNumber++) {
            if (!ports.get(indexOf(portNumber))) {
                ports.set(indexOf(portNumber));
                return portNumber;
            }
        }
        return -1;
    }

    private int indexOf(int portNumber) {
        return portNumber - minimumPortNumber;
    }

    public void freePort(int portNumber) {
        ports.clear(indexOf(portNumber));
    }
}
