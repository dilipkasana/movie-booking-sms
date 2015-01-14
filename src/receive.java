import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

public class receive {

    //public static final String sp = null;
    static InputStream in;
    static OutputStream out;
    String lastIndexRead;
    String senderNum;
    String smsMsg;
    static int flag = 0;
    static SerialPort sp;

    // setup.port_name="COM4";
    //setup.br=921600;
    // ,setup.db,setup.sb,setup.pb

    static void _receive() {
        // TODO Auto-generated method stub
        try {
            flag = 1;
            //            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("serial0");
            // CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(setup.port_name);
            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(setup.port_name);
            sp = (SerialPort)portId.open("Sms_GSM", 0);
            sp.setSerialPortParams(setup.br, setup.db, setup.sb, setup.pb);
            sp.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            in = sp.getInputStream();
            out = sp.getOutputStream();
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/nowsms";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement st = conn.createStatement();

            // modem reset
            //         sendAndRecv("+++AT", 30);       // delay for 20 sec/10
            //         sendAndRecv("AT&F", 30);
            //        sendAndRecv("ATE0", 30);        // echo off
            //         sendAndRecv("AT +CMEE=1", 30);   // verbose error messages
            //          sendAndRecv("AT+CMGF=1", 70);
            // sendAndRecv("AT+CMGF=1", 70);   // set pdu mode
            // sendAndRecv("AT+CMGS=" + 11 + "\r", 30);
            //  sendAndRecv( "0011000C911918400465860000AA0145" + "\u001A", 150);
            sendAndRecv("atz", 30);
            sendAndRecv("ath0", 30);
            sendAndRecv("AT+CMGF=1", 70);
            sendAndRecv("AT+CPMS=\"SM\",\"SM\",\"SM\"", 150);
            //"REC UNREAD","REC READ","STO UNSENT","STO SENT","ALL")
            // String delete=sendAndRecv( "AT+CMGD=?", 150);
            String s = sendAndRecv("AT+CMGL=\"ALL\"", 150);
            String ss = s;
            while (ss.length() > 30) {

                // System.out.println("hiiiiiii...."+ss);
                //System.out.println("hiiiiiii"+ss.indexOf("+CMGL: "));
                String id = ss.substring(ss.indexOf("+CMGL: ") + 7, ss.indexOf(","));
                // System.out.println("id===="+id);
                ss = ss.substring(ss.indexOf(",") + 1);
                ss = ss.substring(ss.indexOf(",") + 1);
                // System.out.println("hiiiiiii...."+ss);
                String mobile = ss.substring(ss.indexOf("\"") + 1, ss.indexOf(",") - 1);
                //System.out.println("hiiiiiii...."+mobile);
                ss = ss.substring(ss.indexOf("\"") + 1);
                ss = ss.substring(ss.indexOf("\"") + 1);
                ss = ss.substring(ss.indexOf("\"") + 1);
                ss = ss.substring(ss.indexOf("\"") + 3);
                String msg;
                if (ss.indexOf("+CMGL: ") != -1) {
                    msg = ss.substring(0, ss.indexOf("+CMGL: ") - 2);
                } else {
                    msg = ss.substring(0, ss.indexOf("OK") - 4);
                }
                // System.out.println("hiiiiiii...."+ss);
                //System.out.println("hiiiiiii...."+msg);
                ss = ss.substring(msg.length());
                sendAndRecv("AT+CMGD=" + id, 150);
                System.out.println(id + "\t" + mobile + "\t" + msg);
                if (mobile.indexOf("+91") > -1) {
                    String msgprefix;
                    if (msg.indexOf(" ") != -1) {
                        msgprefix = msg.substring(0, msg.indexOf(" "));
                    } else {
                        msgprefix = msg;
                    }
                    if (msgprefix.equals(msg) && (msg.length() > 15 || msg.length() == 0)) {
                        msgprefix = "INVALID";
                        msg = "INVALID";
                    }

                    st.executeUpdate("insert into inbox( sender, smsprefix ,fullsms) values('" + mobile + "', '"
                            + msgprefix + "', '" + msg + "')");

                }

            }

            //            sendAndRecv("AT V1E0S0=0&D2&C1", 1000000);

        } catch (Exception e) {
            System.out.println("Exception " + e);
            flag = 2;
            System.exit(1);
        }
        sp.close();
        flag = 0;
    }

    public static String sendAndRecv(String s, int timeout) {
        try {
            // clean serial port input buffer
            in.skip(in.available());
            System.out.println("=> " + s);
            s = s + "\r"; // add CR
            out.write(s.getBytes());
            out.flush();

            String strIn = new String();
            for (int i = 0; i < timeout; i++) {
                System.out.println(".........");
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

    /**
     * @param args
     */
    /*public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	//receive("COM4",921600,8,1,0);
    	receive();
    }*/

}
