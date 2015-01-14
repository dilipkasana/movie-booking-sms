import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

public class send {

    //public static final String sp = null;
    InputStream in;
    OutputStream out;
    String lastIndexRead;
    String senderNum;
    String smsMsg;
    static int flag = 0;
    SerialPort sp;

    // @SuppressWarnings("restriction")
    send(String porta, int baudrate, int databit, int stopbit, int paritybit) {
        try {
            flag = 1;
            //            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("serial0");
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(porta);
            sp = (SerialPort)portId.open("Sms_GSM", 0);
            sp.setSerialPortParams(baudrate, databit, stopbit, paritybit);
            sp.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            in = sp.getInputStream();
            out = sp.getOutputStream();

            // modem reset
            sendAndRecv("+++AT", 30); // delay for 20 sec/10
            sendAndRecv("AT&F", 30);
            sendAndRecv("ATE0", 30); // echo off
            sendAndRecv("AT +CMEE=1", 30); // verbose error messages
            sendAndRecv("AT+CMGF=1", 70);
            sendAndRecv("AT+CMGF=0", 70); // set pdu mode
            //            sendAndRecv("AT V1E0S0=0&D2&C1", 1000000);

        } catch (Exception e) {
            System.out.println("Exception " + e);
            flag = 2;
            System.exit(1);
        }
    }

    public String sendAndRecv(String s, int timeout) {
        try {
            // clean serial port input buffer
            in.skip(in.available());
            System.out.println("=> " + s);
            s = s + "\r"; // add CR
            out.write(s.getBytes());
            out.flush();

            String strIn = new String();
            for (int i = 0; i < timeout; i++) {
                System.out.println("....wait  ");
                int numChars = in.available();
                if (numChars > 0) {
                    byte[] bb = new byte[numChars];
                    in.read(bb, 0, numChars);
                    strIn += new String(bb);
                }
                // start exit conditions
                // ---------------------
                if (strIn.indexOf(">\r\n") != -1) {
                    break;
                }

                if (strIn.indexOf("OK\r\n") != -1) {
                    break;
                }

                if (strIn.indexOf("ERROR") != -1) { // if find 'error' wait for CR+LF
                    if (strIn.indexOf("\r\n", strIn.indexOf("ERROR") + 1) != -1) {
                        break;
                    }
                }

                Thread.sleep(100); // delay 1/10 sec
            }

            System.out.println("<= " + strIn);

            if (strIn.length() == 0) {
                return "ERROR: len 0";
            }

            return strIn;
        } catch (Exception e) {
            System.out.println("send e recv Exception " + e);
            return "ERROR: send e recv Exception";
        }
    }
}
