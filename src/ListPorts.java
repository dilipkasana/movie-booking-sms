/* program*/

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

public class ListPorts {
    static String p[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            ""};
    static int i = 0;

    {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier)ports.nextElement();
            String type;
            switch (port.getPortType()) {
                case CommPortIdentifier.PORT_PARALLEL:
                    type = "Parallel";
                    break;
                case CommPortIdentifier.PORT_SERIAL:
                    type = "Serial";
                    break;
                default: /// Shouldn't happen
                    type = "Unknown";
                    break;
            }
            p[i] = port.getName();
            System.out.println(p[i] + ": " + type);
            i++;
        }
    }
}
