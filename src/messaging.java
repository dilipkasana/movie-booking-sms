import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * This application is intended to send and receive messages 
 * in the following specified formats 
 * * <br>
 * Starting with HELP/help and after containing anything else<br>
 * Starting with INFO/info and after it valid MOVIENAME or THEATRENAME
 * specified as in movie table and after that a date containing in movie table specified in dd-mm-yyyy<br>
 * Starting with ASK/ask and after it valid THEATRENAME and after it valid MOVIENAME
 * and then date and timing as in movie table<br>
 * Starting with BOOK/book and after it show_id and then no. of tickets<br>
 * For instance
 *
 ******************************************************************************************************************
 * HELP SMS <br>
 * INFO DON2 18-01-2012 <br>
 * INFO INOX 18-01-2012 <br>
 * ASK INOX DON2 18-01-2012 E <br>
 * BOOK 571 3 <br>
 * *****************************************************************************************************************

 * *****************************************************************************************************************
 */
public class messaging {
    music helloJMF = new music();

    private static messaging _messaging = null;

    public static messaging getInstance() {
        if (_messaging == null) {
            try {
                applyDBPatch();
            } catch (Exception e) {

            }
            _messaging = new messaging();
        }
        return _messaging;
    }

    private static void applyDBPatch() throws ClassNotFoundException, SQLException {
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost";
        Class.forName(myDriver);
        Connection connection = DriverManager.getConnection(myUrl, "root", "");
        PrintWriter printWriter = new PrintWriter(System.out);
        SqlRunner sqlRunner = new SqlRunner(connection, printWriter, printWriter, true, true);
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(new File("./src/sql/nowsms.sql")));
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
        sqlRunner.runScript(reader);
    }

    private messaging() {
        try {
            Thread.sleep(10000);

        } catch (InterruptedException ie) {
            // TODO Auto-generated catch block
            System.out.println(ie.getMessage());
        }

        //public static void main(String[] args)
        // { 
        //@Variable j,k,l are using temporary store sending message

        Random rand;
        rand = new Random();
        int book_id = rand.nextInt(10000);
        String j = "", k = "", l = "";
        while (true) {
            try {
                // create our mysql database connection to our database nowsms
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/nowsms";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "");
                Statement sttemp = conn.createStatement();
                Statement stalert = conn.createStatement();
                Statement stoutbox = conn.createStatement();
                Statement stdelete = conn.createStatement();
                Calendar currentDate = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH mm");
                String dateNow = formatter.format(currentDate.getTime());
                System.out.println("Now the date is :=>  " + dateNow);

                String[] tempdt;
                String tempdelimiter1 = " ";
                tempdt = dateNow.split(tempdelimiter1, 5);
                int tempdtdd = Integer.parseInt(tempdt[2]);
                int tempdtyyyy = Integer.parseInt(tempdt[0]);
                int tempdtmm = Integer.parseInt(tempdt[1]);
                int temphh = Integer.parseInt(tempdt[3]);
                int tempmm = Integer.parseInt(tempdt[4]);
                int tempdateNow = tempdtyyyy * 10000 + tempdtmm * 100 + tempdtdd;
                System.out.println(tempdateNow);
                int temptimeNow = temphh * 100 + tempmm;
                System.out.println(temptimeNow);
                //
                //
                //
                //deleting all old entries from movie table
                sttemp.executeUpdate("DELETE FROM movie WHERE date<=" + tempdateNow + "");
                System.out.println("deleted entry less than or equal date " + tempdtdd + "/" + tempdtmm + "/"
                        + tempdtyyyy);
                System.out.println("total " + setup.total_msg + " " + "msgs sended till now" + "\n");
                //
                //
                //
                //
                //Sending alert messages
                //
                //
                ResultSet rsalert = stalert.executeQuery("SELECT DISTINCT mobile,msg,id FROM alert WHERE  date<='"
                        + tempdateNow + "' AND time<='" + temptimeNow + "'");
                while (rsalert.next()) {
                    int tempid = rsalert.getInt("id");
                    String sender1 = rsalert.getString("mobile");
                    String sms = rsalert.getString("msg");
                    sms = sms + " .k";
                    stdelete.executeUpdate("DELETE FROM alert WHERE id=" + tempid + "");
                    stoutbox.executeUpdate("insert into outbox( receiver, replysms) values('" + sender1 + "', '" + sms
                            + "')");
                    System.out.println("1 alert msg updated successfuly");
                }
                // create the java statement
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                Statement st2 = conn.createStatement();
                Statement st3 = conn.createStatement();
                Statement st4 = conn.createStatement();
                Statement st5 = conn.createStatement();
                Statement st6 = conn.createStatement();
                Statement st7 = conn.createStatement();
                Statement st8 = conn.createStatement();
                Statement st9 = conn.createStatement();
                Statement st10 = conn.createStatement();
                Statement st11 = conn.createStatement();
                Statement st12 = conn.createStatement();

                // our SQL SELECT query. 
                // if you only need a few columns, specify them by name instead of using "*"
                //importing all inbox messages for processing 
                String query = "SELECT * FROM inbox";
                // Statement st13 = conn.createStatement();
                // Statement st14 = conn.createStatement();
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset.here only one msg at a time is processed
                while (rs.next()) {

                    //System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk
                    int id1 = rs.getInt("id");
                    String sender1 = rs.getString("sender");
                    String smsprefix1 = rs.getString("smsprefix");
                    String fullsms1 = rs.getString("fullsms");
                    String msgdate1 = rs.getString("msgdate");
                    String msgtime1 = rs.getString("msgtime");
                    //
                    //we are interesting to delete input message so early 
                    //because if it has error then it will not generate error again.......
                    st7.executeUpdate("DELETE FROM inbox WHERE id=" + id1 + "");
                    int flag = 0;
                    int errmsg = 0, errbooking = 0;
                    //
                    //HELP HELP 
                    //
                    if (smsprefix1.equalsIgnoreCase("HELP")) {
                        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                        j = "cinemaname :";
                        k = "Moviename :";
                        //getting cinema hall name
                        ResultSet rs1 = st3.executeQuery("SELECT DISTINCT  theatre FROM movie");
                        while (rs1.next()) {

                            String a = rs1.getString("theatre");
                            //String b="";

                            j = j + a + ", ";

                            System.out.format("%s\n", j);
                        }
                        //getting movie name
                        ResultSet rs2 = st4.executeQuery("SELECT DISTINCT movie FROM movie");
                        while (rs2.next()) {

                            String c = rs2.getString("movie");

                            k = k + c + ", ";

                            System.out.format("%s\n", k);
                        }
                        j = j
                                + "\n"
                                + k
                                + "\n"
                                + "for more reply <INFO moviename date> or <INFO theatrename date> for Ex. <INFO INOX 18-01-2012";
                        System.out.format("%s\n", j);

                    }
                    //
                    //INFO MOVIENAME DATE
                    //        
                    if (smsprefix1.equalsIgnoreCase("INFO")) {
                        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkk");
                        int tempsyntax1 = 0, tempsyntax2 = 0;

                        //shecking the syntax of incoming info msg
                        char[] cArray = fullsms1.toCharArray();
                        for (char c : cArray) {
                            if (c == 32) {
                                tempsyntax1++;
                            }
                            if (c == 45 && tempsyntax1 == 2) {
                                tempsyntax2++;
                            }
                        }
                        //if syntax is true
                        if (tempsyntax1 == 2 && tempsyntax2 == 2) {

                            String[] temp;
                            String delimiter = " ";
                            //Splitting the msg in words
                            temp = fullsms1.split(delimiter, 3);
                            //System.out.println(temp[1]);
                            //checking spelling of moviename
                            ResultSet rsthetre = st9.executeQuery("SELECT DISTINCT theatre FROM movie");
                            while (rsthetre.next()) {

                                String checktheatre = rsthetre.getString("theatre");
                                errmsg = 2;
                                if (checktheatre.equals(temp[1])) {
                                    flag = 1;
                                    errmsg = 0;
                                    break;
                                }
                                if (errmsg == 2) {
                                    l = "spelling error in moviename";
                                }
                                //System.out.format("%s\n",k);
                            }
                            //System.out.println(temp[2]);
                            //checking syntax of date
                            if (!(temp[2].contains("-") && temp[2].length() == 10)) {
                                errmsg = 1;
                                l = "syntax error in date";
                            }
                            //if incoming date  is error free then checking date 
                            //Actually we are interesting in changing date into yyyymmdd format to compare eachtime
                            if (errmsg == 0) {
                                String[] temp1;
                                String delimiter1 = "-";
                                temp1 = temp[2].split(delimiter1, 3);
                                int dd = Integer.parseInt(temp1[0]);
                                int yyyy = Integer.parseInt(temp1[2]);
                                int mm = Integer.parseInt(temp1[1]);
                                int tempdate = yyyy * 10000 + mm * 100 + dd;
                                System.out.println(tempdate);
                                ResultSet rsdate = st10.executeQuery("SELECT DISTINCT date FROM movie");
                                while (rsdate.next()) {

                                    int date = rsdate.getInt("date");
                                    errmsg = 3;
                                    if (date == tempdate) {
                                        //flag=1;
                                        errmsg = 0;
                                        break;
                                    }
                                    //if(tempdate==date)
                                }
                            }
                            if (errmsg == 3) {
                                l = "info on this date is not available currently";
                                flag = 1;
                            }
                            // if all values in INFO MOVIENAME DATE then
                            if (errmsg == 0) {
                                //if(temp[2].contains())
                                //int tempdate=Integer.parseInt(temp[2]);
                                String[] temp1;
                                String delimiter1 = "-";
                                temp1 = temp[2].split(delimiter1, 3);
                                int dd = Integer.parseInt(temp1[0]);
                                int yyyy = Integer.parseInt(temp1[2]);
                                int mm = Integer.parseInt(temp1[1]);
                                int tempdate = yyyy * 10000 + mm * 100 + dd;
                                System.out.println(tempdate);

                                String p = temp[1];
                                j = p + "\n";
                                ResultSet rs1 = st3.executeQuery("SELECT DISTINCT movie FROM movie WHERE theatre='"
                                        + temp[1] + "' AND date='" + tempdate + "' AND ticket>0");
                                while (rs1.next()) {

                                    String a = rs1.getString("movie");
                                    System.out.println(".k.k.k.k.k.k.k.k.k.k");

                                    j = j + ":" + a + " SHOW:";
                                    ResultSet rs2 = st4
                                            .executeQuery("SELECT DISTINCT timing FROM movie WHERE theatre='" + temp[1]
                                                    + "' AND date='" + tempdate + "' AND movie ='" + a
                                                    + "' AND ticket>0 ORDER BY timing ASC");
                                    while (rs2.next()) {
                                        String b = rs2.getString("timing");
                                        j = j + "" + b;

                                    }
                                    j = j + " :Ticket Available\n";
                                }
                                j = j
                                        + "to ask more reply <ASK theatrename moviename date timing> for ex. <ASK INOX DON2 18-01-2012 E> ";
                                System.out.format("%s\n", j);
                            } else {
                                j = "" + l;
                                System.out.format("%s\n", j);
                            }
                        } else {
                            j = "error in msg syntax try as INFO INOX 18-01-2012";
                            System.out.format("%s\n", j);
                        }
                    }
                    //
                    //INFO CINEMANAME DATE            
                    //
                    if (flag == 0) {
                        if (smsprefix1.equalsIgnoreCase("INFO")) {
                            System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKK");
                            // ifinfo=1;
                            // errorinfo=1;
                            int tempsyntax1 = 0, tempsyntax2 = 0;
                            // ifinfo=1;
                            // errorinfo=1;
                            errmsg = 0;
                            char[] cArray = fullsms1.toCharArray();
                            for (char c : cArray) {
                                if (c == 32) {
                                    tempsyntax1++;
                                }
                                if (c == 45 && tempsyntax1 == 2) {
                                    tempsyntax2++;
                                }
                            }
                            if (tempsyntax1 == 2 && tempsyntax2 == 2) {
                                String[] temp;
                                String delimiter = " ";
                                temp = fullsms1.split(delimiter, 3);
                                System.out.println(temp[1]);
                                System.out.println(temp[2]);
                                ResultSet rsmovie = st8.executeQuery("SELECT DISTINCT movie FROM movie");
                                while (rsmovie.next()) {

                                    String checkmovie = rsmovie.getString("movie");
                                    errmsg = 2;
                                    if (checkmovie.equals(temp[1])) {
                                        errmsg = 0;
                                        break;
                                    }
                                    if (errmsg == 2) {
                                        l = "spelling error in moviename  or theatrename \n be sure that name are case sensitive";
                                    }
                                    //System.out.format("%s\n",k);
                                }
                                System.out.println(temp[2]);
                                if (!(temp[2].contains("-") && temp[2].length() == 10)) {
                                    errmsg = 1;
                                    l = "syntax error in date";
                                }

                                if (errmsg == 0) {
                                    String[] temp1;
                                    String delimiter1 = "-";
                                    temp1 = temp[2].split(delimiter1, 3);
                                    int dd = Integer.parseInt(temp1[0]);
                                    int yyyy = Integer.parseInt(temp1[2]);
                                    int mm = Integer.parseInt(temp1[1]);
                                    int tempdate = yyyy * 10000 + mm * 100 + dd;
                                    System.out.println(tempdate);
                                    ResultSet rsdate = st10.executeQuery("SELECT DISTINCT date FROM movie");
                                    while (rsdate.next()) {

                                        int date = rsdate.getInt("date");
                                        errmsg = 3;
                                        if (date == tempdate) {
                                            //flag=1;
                                            errmsg = 0;
                                            break;
                                        }
                                        //if(tempdate==date)
                                    }
                                }
                                if (errmsg == 3) {
                                    l = "info on this date is not available currently";
                                    flag = 1;
                                }
                                if (errmsg == 0) {
                                    //int tempdate=Integer.parseInt(temp[2]);
                                    if (temp[2].contains("-") && temp[2].length() == 10) {
                                        String[] temp1;
                                        String delimiter1 = "-";
                                        temp1 = temp[2].split(delimiter1, 3);
                                        int dd = Integer.parseInt(temp1[0]);
                                        int yyyy = Integer.parseInt(temp1[2]);
                                        int mm = Integer.parseInt(temp1[1]);
                                        int tempdate = yyyy * 10000 + mm * 100 + dd;
                                        System.out.println(tempdate);

                                        String p = temp[1];
                                        j = p + "\n";
                                        ResultSet rs3 = st1
                                                .executeQuery("SELECT DISTINCT theatre FROM movie WHERE movie='"
                                                        + temp[1] + "' AND date='" + tempdate + "' AND ticket>0");
                                        while (rs3.next()) {

                                            String a = rs3.getString("theatre");
                                            System.out.println("qqqqqqqqqqq");

                                            j = j + ":" + a + " SHOW:";
                                            ResultSet rs4 = st2
                                                    .executeQuery("SELECT DISTINCT timing FROM movie WHERE movie='"
                                                            + temp[1] + "' AND theatre ='" + a + "' AND date='"
                                                            + tempdate + "' AND ticket>0 ORDER BY timing ASC");
                                            while (rs4.next()) {
                                                String b = rs4.getString("timing");
                                                j = j + "" + b;

                                            }
                                            j = j + " :Ticket Available\n";
                                        }
                                        j = j
                                                + "to ask more reply <ASK theatrename moviename date timing> for ex. <ASK INOX DON2 18-01-2012 E> ";
                                        System.out.format("%s\n", j);
                                    }
                                } else {
                                    j = "" + l;
                                    System.out.format("%s\n", j);
                                }
                            } else {
                                j = "error in msg syntax try as INFO DON2 18-01-2012";
                                System.out.format("%s\n", j);
                            }
                        }
                    }

                    //
                    //ASK CINEMANAME MOVIENAME DATE TIMING 
                    //             

                    if (smsprefix1.equalsIgnoreCase("ASK")) {
                        System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPP");
                        int tempsyntax1 = 0, tempsyntax2 = 0, errtempmsg = 0;
                        char[] cArray = fullsms1.toCharArray();
                        for (char c : cArray) {
                            if (c == 32) {
                                tempsyntax1++;
                            }
                            if (c == 45 && tempsyntax1 == 3) {
                                tempsyntax2++;
                            }
                        }
                        if (tempsyntax1 == 4 && tempsyntax2 == 2) {

                            String[] temp;
                            String delimiter = " ";
                            temp = fullsms1.split(delimiter, 5);

                            ResultSet rsmovie = st8.executeQuery("SELECT DISTINCT movie FROM movie");
                            while (rsmovie.next()) {

                                String checkmovie = rsmovie.getString("movie");
                                errtempmsg = 1;
                                if (checkmovie.equals(temp[2])) {
                                    errtempmsg = 0;
                                    break;
                                }
                                if (errtempmsg == 1) {
                                    l = "spelling error in moviename \n be sure that name are case sensitive";
                                }
                            }

                            if (errtempmsg == 0) {
                                ResultSet rsthetre = st9.executeQuery("SELECT DISTINCT theatre FROM movie");
                                while (rsthetre.next()) {

                                    String checktheatre = rsthetre.getString("theatre");
                                    errtempmsg = 2;
                                    if (checktheatre.equals(temp[1])) {
                                        //flag=1;
                                        errtempmsg = 0;
                                        break;
                                    }
                                    if (errtempmsg == 2) {
                                        l = "spelling error in theatrename";
                                    }
                                    //System.out.format("%s\n",k);
                                }
                            }
                            if (!(temp[3].contains("-") && temp[3].length() == 10)) {
                                errtempmsg = 3;
                                l = "syntax error in date";
                            }

                            if (errtempmsg == 0) {
                                String[] temp1;
                                String delimiter1 = "-";
                                temp1 = temp[3].split(delimiter1, 3);
                                int dd = Integer.parseInt(temp1[0]);
                                int yyyy = Integer.parseInt(temp1[2]);
                                int mm = Integer.parseInt(temp1[1]);
                                int tempdate = yyyy * 10000 + mm * 100 + dd;
                                System.out.println(tempdate);
                                ResultSet rsdate = st10.executeQuery("SELECT DISTINCT date FROM movie");
                                while (rsdate.next()) {

                                    int date = rsdate.getInt("date");
                                    errtempmsg = 4;
                                    if (date == tempdate) {
                                        //flag=1;
                                        errtempmsg = 0;
                                        break;
                                    }
                                    //if(tempdate==date)
                                }
                            }
                            if (errtempmsg == 4) {
                                l = "info on this date is not available currently";
                                //flag=1;
                            }
                            System.out.println(errtempmsg);
                            //int aInt = Integer.parseInt(temp[4]);
                            //int tempdate=Integer.parseInt(temp[3]);

                            if (errtempmsg == 0) {
                                String[] temp1;
                                String delimiter1 = "-";
                                temp1 = temp[3].split(delimiter1, 3);
                                int dd = Integer.parseInt(temp1[0]);
                                int yyyy = Integer.parseInt(temp1[2]);
                                int mm = Integer.parseInt(temp1[1]);
                                int tempdate = yyyy * 10000 + mm * 100 + dd;
                                System.out.println(tempdate);
                                //System.out.println("lklklklkklkkl"+temp[4]);

                                //String p=temp[1];
                                //j=p+"\n";
                                ResultSet rs1 = st3
                                        .executeQuery("SELECT DISTINCT price,id,ticket FROM movie WHERE theatre='"
                                                + temp[1] + "' AND movie='" + temp[2] + "' AND timing='" + temp[4]
                                                + "' AND date='" + tempdate + "' AND ticket>0");
                                if (rs1.next()) {

                                    String a = rs1.getString("price");
                                    String b = rs1.getString("id");
                                    String c = rs1.getString("ticket");
                                    //int intprice = Integer.parseInt(a);
                                    // int intid = Integer.parseInt(a);
                                    // bInt=bInt*aInt;
                                    //String aString =Integer.toString(bInt);

                                    j = temp[1] + " " + temp[2] + "\nAvailable Tickets:" + c + "\nprice per ticket:"
                                            + a + "\n To book this show reply <BOOK " + b
                                            + " noOFtickets> \n for EX: BOOK " + b + " 3" + "\nThanks\n .k";
                                    // j="Ticket no.=xxxxx\ncinema:"+temp[1]+"\nmovie:"+temp[2]+"\ntiming:"+temp[3]+"\nseats"+temp[4]+"\nAmount:"+bInt+"\nThanks";
                                    System.out.format("%s\n", j);
                                }
                            } else {
                                j = "" + l;
                                System.out.format("%s\n", j);
                            }
                        } else {
                            j = "error in msg syntax try as ASK INOX DON2 18-01-2012 E";
                            System.out.format("%s\n", j);
                        }
                    }

                    //
                    //BOOK SHOW_ID TICKETS
                    //             
                    if (smsprefix1.equalsIgnoreCase("BOOK")) {
                        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                        int tempsyntax1 = 0, tempsyntax2 = 0;
                        char[] cArray = fullsms1.toCharArray();
                        for (char c : cArray) {
                            if (c == 32) {
                                tempsyntax1++;
                            } else if (tempsyntax1 == 2) {
                                tempsyntax2++;
                            }

                        }
                        if (tempsyntax2 > 0) {
                            System.out.println("kkkkkkkkkkk");

                            String[] temp;
                            String delimiter = " ";
                            temp = fullsms1.split(delimiter, 3);
                            System.out.println(temp[1]);
                            int show_id = Integer.parseInt(temp[1]);
                            System.out.println(temp[2]);
                            int noOFticket = Integer.parseInt(temp[2]);
                            //int tempdate=Integer.parseInt(temp[4]);
                            //int dd=tempdate%100;
                            //int yyyy=tempdate/10000;
                            //int mm=(tempdate-yyyy*10000)/100;
                            //tempdate=yyyy*10000+mm*100+dd;
                            //String p=temp[1];
                            //j=p+"\n";
                            ResultSet rs1 = st3
                                    .executeQuery("SELECT DISTINCT theatre,movie,date,timing,price,ticket FROM movie WHERE id='"
                                            + show_id + "' AND ticket>0");
                            if (rs1.next()) {
                                errbooking = 1;
                                String theatrename = rs1.getString("theatre");
                                String moviename = rs1.getString("movie");
                                String date = rs1.getString("date");
                                int tempdate = Integer.parseInt(date);
                                int dd = tempdate % 100;
                                int yyyy = tempdate / 10000;
                                int mm = (tempdate - yyyy * 10000) / 100;
                                //tempdate=yyyy*10000+mm*100+dd;
                                String timing = rs1.getString("timing");
                                String price = rs1.getString("price");
                                int tempprice = Integer.parseInt(price);
                                String ticket = rs1.getString("ticket");
                                int tempticket = Integer.parseInt(ticket);
                                int rest_ticket = tempticket - noOFticket;
                                book_id++;
                                //int intprice = Integer.parseInt(a);
                                // int intid = Integer.parseInt(a);
                                int total_price = noOFticket * tempprice;
                                //String aString =Integer.toString(bInt);

                                j = "Book.id:" + book_id + "\nCinema:" + theatrename + "\nMovie:" + moviename
                                        + "\nDate:" + dd + "-" + mm + "-" + yyyy + "\ntiming:" + timing
                                        + "\nno_of_seat:" + noOFticket + "\nPrice:" + total_price + "\nThanks\n.k";
                                st11.executeUpdate("insert into book( book_id, show_id, mobile, tickets, price) values('"
                                        + book_id
                                        + "', '"
                                        + show_id
                                        + "', '"
                                        + sender1
                                        + "', '"
                                        + noOFticket
                                        + "', '"
                                        + total_price + "')");
                                st12.executeUpdate("UPDATE movie SET ticket='" + rest_ticket + "' WHERE id='" + show_id
                                        + "' ");
                                System.out.println("1 row affected");
                                System.out.format("%s\n", j);
                            }
                            if (errbooking == 0) {
                                j = "message for booking is not matched either id or seat not available for this show_id";
                                System.out.format("%s\n", j);
                            }
                        } else {
                            j = "error in msg syntax try as BOOK 571 3";
                            System.out.format("%s\n", j);
                        }
                    }

                    if (!(smsprefix1.equalsIgnoreCase("HELP") || smsprefix1.equalsIgnoreCase("INFO")
                            || smsprefix1.equalsIgnoreCase("ASK") || smsprefix1.equalsIgnoreCase("BOOK"))) {
                        j = "welcome...INVALID FORMAT.. we use date in dd-mm-yyyy format and showtiming in M,A,E,N where M:mrning,A:aftrnoon,E:evning,N:nignt reply <HELP> to get mor";
                    }

                    st5.executeUpdate("insert into outbox( receiver, replysms) values('" + sender1 + "', '" + j + "')");

                    System.out.format("%s, %s, %s, %s, %s, %s\n", id1, sender1, smsprefix1, fullsms1, msgdate1,
                            msgtime1);
                    st6.executeUpdate("insert into draft( sender, smsprefix, fullsms, msgdate, msgtime) values('"
                            + sender1 + "', '" + smsprefix1 + "', '" + fullsms1 + "', '" + msgdate1 + "', '" + msgtime1
                            + "')");

                    System.out.println("1 row affected");
                }
                st.close();
                /* URL sendsms = new URL("http://localhost/try/sendsmsscript.php?a=hi");
                 URLConnection yc = sendsms.openConnection();
                 BufferedReader in = new BufferedReader(
                 new InputStreamReader(
                 yc.getInputStream()));
                 String inputLine;
                 while ((inputLine = in.readLine()) != null) 
                  System.out.println(inputLine);
                     in.close();
                     System.out.println("KKKKKKKKKKKKKKKKK SUCCESSFUL");*/
                //
                //WAITING FOR ICOMING MESSAGE
                //U CAN SAVE 5 seconds BY SETTING Thread.sleep(1000); OR  Thread.sleep(0000);
                //WE ARE USING IT NOT TO BUSY CPU ALWAYS BECAUSE WE ARE NOT GETTING MASSAGES SIMULTANOUSLY
                try {
                    Thread.sleep(5000);

                } catch (InterruptedException ie) {
                    // TODO Auto-generated catch block
                    System.out.println(ie.getMessage());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
            String dateNow = formatter1.format(currentDate.getTime());
            String timeNow = formatter2.format(currentDate.getTime());
            if (send.flag == 1) {
                send.flag = 0;

            }

            try {
                Thread.sleep(5000);

            } catch (InterruptedException ie) {
                // TODO Auto-generated catch block
                System.out.println(ie.getMessage());
            }

            if (setup.connection != 0) {
                setup.status.setText("connected");
                setup.connection = 3;
                receive._receive();
                try {
                    // create our mysql database connection to our database nowsms
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    String myUrl = "jdbc:mysql://localhost/nowsms";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root", "");
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();

                    Statement st5 = conn.createStatement();

                    // our SQL SELECT query. 
                    // if you only need a few columns, specify them by name instead of using "*"
                    //importing all inbox messages for processing 
                    String query = "SELECT * FROM outbox";
                    // Statement st13 = conn.createStatement();
                    // Statement st14 = conn.createStatement();
                    // execute the query, and get a java resultset
                    ResultSet rs = st.executeQuery(query);

                    // iterate through the java resultset.here only one msg at a time is processed
                    while (rs.next()) {
                        int id1 = rs.getInt("id");
                        String mobile = rs.getString("receiver");
                        String message = rs.getString("replysms");
                        st1.executeUpdate("DELETE FROM outbox WHERE id=" + id1 + "");

                        try {
                            Thread.sleep(1000);

                        } catch (InterruptedException ie) {
                            // TODO Auto-generated catch block
                            System.out.println(ie.getMessage());
                        }
                        sendsms._sendsms(message, mobile);

                        System.out.println("hhhhhhhhhhhhhh");
                        st5.executeUpdate("insert into sentitem( mobile, msg ,date ,time) values('" + mobile + "', '"
                                + message + "', '" + dateNow + "', '" + timeNow + "')");
                    }
                    st.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
            }
        }

    }

    public static void main(String[] args) {
        messaging messaging = new messaging();

        System.out.println(messaging.hashCode());
    }
}
