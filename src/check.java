import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;

public class check {

    //public static final String sp = null;
    static InputStream in;
    static OutputStream out;
    String lastIndexRead;
    String senderNum;
    String smsMsg;
    static int flag = 0;
    static SerialPort sp;
    static int status = 0;
    Thread aThread = null;

    public static boolean checkCheck(String porta, int baudrate, int databit, int stopbit, int paritybit) {
        try {

            //            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("serial0");

            CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(porta);
            sp = (SerialPort)portId.open("Sms_GSM", 0);
            sp.setSerialPortParams(baudrate, databit, stopbit, paritybit);
            sp.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            in = sp.getInputStream();
            out = sp.getOutputStream();

            // modem reset

            sendAndRecv("+++AT", 30); // delay for 20 sec/10
            status++;
            sendAndRecv("AT&F", 30);
            status++;
            sendAndRecv("ATE0", 30); // echo off
            status++;
            sendAndRecv("AT +CMEE=1", 30); // verbose error messages
            status++;
            sendAndRecv("AT+CMGF=1", 70);
            status++;
            sendAndRecv("AT+CMGF=0", 70); // set pdu mode
            status++;
            //            sendAndRecv("AT V1E0S0=0&D2&C1", 1000000);

        } catch (Exception e) {
            System.out.println("Exception " + e);

            return false;
            //System.exit(1);
        }
        sp.close();

        if (status == 6) {
            return true;
        } else {
            return false;
        }
    }

    public static String sendAndRecv(String s, int timeout) {
        try {
            // clean serial port input buffer
            in.skip(in.available());
            System.out.println("=> " + s);
            s = s + "\r"; // add CR
            System.out.println("\n1");
            out.write(s.getBytes());
            /*       
                   int time=5;//in seconds
              	 long patience = 1000 * time;
              	 
              	 System.out.println("Starting MessageLoop thread");
                   long startTime = System.currentTimeMillis();
                   Thread t = new Thread(chck(s));
                   t.start();
                   System.out.println("Waiting for MessageLoop thread to finish");
                   while (t.isAlive()) {
                   	t.join(1000);
                       if (((System.currentTimeMillis() - startTime) > patience)
                             && t.isAlive()) {
                      	 System.out.println("Tired of waiting!");
                      	 t.destroy();
                           //t.interrupt();
                           // Shouldn't be long now
                           // -- wait indefinitely
                           //t.join();
                       }
                   }
                   
                 */

            System.out.println("\n2");
            out.flush();
            System.out.println("\n3");

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

    /* private static Runnable chck(String s) throws IOException {
    	// TODO Auto-generated method stub
    	 try {
             
         	out.write(s.getBytes());
                 Thread.sleep(4000);
              
         } catch (InterruptedException e) {
             
         }
    	return null;
    }*/

    /*public static void main(String[] args){
    	 boolean b=check("COM10",setup.br,setup.db,setup.sb,setup.pb);
    	 System.out.println(status);
     }
     
     
     
     
    }*/
    /*   
       private static class chck
       implements Runnable {
       public void run() {
      	 
           try {
               
          	 boolean b=check("COM4",setup.br,setup.db,setup.sb,setup.pb);
                   Thread.sleep(4000);
                
           } catch (InterruptedException e) {
               
           }
       }
    }
       
       
       public static void main(String[] args) throws InterruptedException {
    	
           long startTime = System.currentTimeMillis();
           Thread t = new Thread(new chck());
           t.start();
            System.out.println("Waiting for checking to finish");
           while (t.isAlive()) {
          	 System.out.println("Still waiting...");
               t.join(1000);
               if (((System.currentTimeMillis() - startTime) > 5000)
                     && t.isAlive()) {
              	 
              	 System.out.println("Tired of waiting");
              	 t.stop();
              	 
                   t.interrupt();
                   t.join();
               }
           }
           System.out.println("Finally!");
      	 
      	 
    	System.out.println(status);
    }*/
}
