public class sendsms {

    public static String _sendsms(String msg, String mobile) {
        if ((mobile.length() == 10)) {
            mobile = "+91" + mobile;
        }
        if (!mobile.subSequence(0, 3).equals("+91")) {
            System.out.println("kkkkkkkkkkkkkkkkkk");
            mobile = "+919413102070";
            System.out.println("kkkkkkkkkkkkkkkkkk________________msg sended to default no's");
        }
        String s = null;
        int no_of_msg = 0;
        if (msg.length() <= 160) {
            no_of_msg = 1;
        }
        if (msg.length() > 160) {
            if (msg.length() % 153 == 0) {
                no_of_msg = msg.length() / 153;
            } else {
                no_of_msg = (msg.length() / 153) + 1;
            }
        }
        //System.out.println(no_of_msg);
        //check stg = new check(setup.port_name,setup.br,setup.db,setup.sb,setup.pb);
        //check stg = new check("COM4",921600,8,1,0);
        if (no_of_msg == 1) {
            setup.total_msg++;
            send ss = new send(setup.port_name, setup.br, setup.db, setup.sb, setup.pb);

            smsdata sms = new smsdata();
            sms.setAsciiTxt(msg);
            sms.setTelNum(mobile);
            s = new String();

            s = ss.sendAndRecv("AT+CMGS=" + ((sms.getCompletePduData().length() / 2)) + "\r", 30);

            if (s.indexOf(">") != -1) {

                s = ss.sendAndRecv("00" + sms.getCompletePduData() + "\u001A", 150);

                System.out.println("before close");
                ss.sp.close();
                System.out.println("after close");
                return s;
            } else {
                return "ERROR";
            }
        }
        if (no_of_msg > 1) {
            send ss = new send(setup.port_name, setup.br, setup.db, setup.sb, setup.pb);
            for (int j = 0; j < no_of_msg; j++) {
                setup.total_msg++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //SerialToGsm stg = new SerialToGsm("COM4");
                String tempmsg;
                if (j != no_of_msg - 1) {
                    tempmsg = (String)msg.subSequence(0, 153);
                } else {
                    tempmsg = msg;
                    //System.out.println(tempmsg );
                }

                if (j != no_of_msg - 1) {
                    msg = msg.substring(153);
                }

                //smsdata sms = new smsdata();
                //sms.setTelNum(mobile);
                //System.out.println(sms.telNum );
                //System.out.println(sms.telNumLen );
                String fullpdu = send_multiple_msg(tempmsg, mobile, no_of_msg, j + 1);
                s = ss.sendAndRecv("AT+CMGS=" + fullpdu.length() / 2 + "\r", 30);
                if (s.indexOf(">") != -1) {
                    s = ss.sendAndRecv("00" + fullpdu + "\u001A", 150);
                }

            }
            System.out.println("before close");
            ss.sp.close();
            System.out.println("after close");

        }

        return s;

    }

    private static String send_multiple_msg(String tempmsg, String mobile, int total_msg, int index_of_this_msg) {
        // TODO Auto-generated method stub

        String str1 = Integer.toHexString(index_of_this_msg); // convert # in string

        if (str1.length() < 2) {
            str1 = '0' + str1;
        }

        str1 = (str1.substring(str1.length() - 2, str1.length()));
        str1 = str1.toUpperCase();
        //System.out.println(str1);

        String str2 = Integer.toHexString(total_msg); // convert # in string

        if (str2.length() < 2) {
            str2 = '0' + str2;
        }

        str2 = (str2.substring(str2.length() - 2, str2.length()));
        str2 = str2.toUpperCase();
        // System.out.println(str2);

        String str3 = Integer.toHexString(index_of_this_msg - 1); // convert # in string

        if (str3.length() < 2) {
            str3 = '0' + str3;
        }

        str3 = (str3.substring(str3.length() - 2, str3.length()));
        str3 = str3.toUpperCase();
        ///System.out.println(str3);

        smsdata sms = new smsdata();
        sms.setTelNum(mobile);
        //System.out.println(sms.telNum );
        // System.out.println(sms.telNumLen );

        String udh = new String();
        udh = "05"; //length of udh
        udh += "00"; //
        udh += "03"; //lenghth of next udh bits
        udh += "00"; //msg refrence
        udh += str2; //no. of total msg
        udh += str1; //index of this msg
                     //System.out.println(udh );
        String pdutxt = new String();
        pdutxt = udh + get_first_hex_after_UDH(udh, tempmsg.substring(0, 1)) + sms.txtToSmsPdu1(tempmsg.substring(1));

        String pduTxtLen = new String();
        pduTxtLen = Integer.toHexString(tempmsg.length() + 7);
        //System.out.println("pdutxtlen="+pduTxtLen);
        if (pduTxtLen.length() < 2) {
            pduTxtLen = "0" + pduTxtLen;
            //System.out.println("pdutxtlen="+pduTxtLen);
        }
        pduTxtLen = pduTxtLen.toUpperCase();

        String pduData = new String();
        pduData = "41"; // first octet of SMS Submit
        pduData += str3; // let telephone set msg reference
        pduData += sms.telNumLen; // tel # length
        pduData += "91"; // tel # is int'l format
        pduData += sms.telNum; // tel Num in GSM format
        pduData += "00"; // protocol identifier TP-PID
        pduData += "00"; // pdu data is encoded 7bit data
        pduData += pduTxtLen; // length of text data
        pduData += pdutxt; // message encoded data

        //System.out.println("pduData="+pduData);
        return pduData;
    }

    private static String get_first_hex_after_UDH(String UDH, String msg_first_letter) {
        smsdata sms = new smsdata();

        //String UDH="050003000301";
        //String msg_first_letter="L";
        int i;
        String str1 = null; //System.out.println(UDH);
        for (i = 0; i < 256; i++) {
            String UDH_and_msg1_in_PDU;
            String UDH_and_msg1_in_txt;

            str1 = Integer.toHexString(i); // convert # in string   
            if (str1.length() < 2) {
                str1 = '0' + str1;
            }
            str1 = (str1.substring(str1.length() - 2, str1.length()));
            str1 = str1.toUpperCase();
            UDH_and_msg1_in_PDU = UDH + str1;
            //System.out.println(UDH_and_msg1_in_PDU);
            UDH_and_msg1_in_txt = sms.smsPduToTxt(UDH_and_msg1_in_PDU);
            //System.out.println(UDH_and_msg1_in_txt);
            //System.out.println(UDH_and_msg1_in_txt.substring(7));
            if ((UDH_and_msg1_in_txt.substring(7)).equals(msg_first_letter)) {
                //System.out.println("llllllllllllllllllllllllll"+i);
                //System.out.println("new Hex value added after UDH======="+str1);
                break;
            }
        }
        return str1;

    }

    /*public static void main(String[] args){
    	sendsms("love love love love love lovelove lovlove love love love love lovelove lovlove love love love love lovelove lovlove love love love love lovelove lovlove love love love love lovelove lovlove love love love love lovelove lov","+918104405668");
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	sendsms("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj","+918104405668");
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	sendsms("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj","+918104405668");
    }*/

}
