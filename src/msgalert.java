import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class msgalert extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // Variables declaration
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextArea msg;
    private JTextField date;
    private JComboBox hh;
    private JComboBox mm;
    private JComboBox receiptent;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton5;
    private JPanel contentPane;
    private String imagedir = "image/";
    private String[] imageFileNames = {"back.png", "tinka.jpg"};
    private ImageIcon icon1, icon2;

    // End of variables declaration

    public msgalert() {
        super();
        create();
        this.setVisible(true);
    }

    private void create() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        msg = new JTextArea();
        date = new JTextField("18-01-2012");
        String hours[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        hh = new JComboBox(hours);
        String minutes[] = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
                "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55",
                "56", "57", "58", "59"};
        mm = new JComboBox(minutes);
        String receiver[] = {"all", "one"};
        receiptent = new JComboBox(receiver);
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton5 = new JButton();
        String image = "/image/19.jpg";
        contentPane = new ImagePanel(image);
        //contentPane = (JPanel)this.getContentPane();
        //
        //
        //
        //icon1 admin
        //

        icon1 = createImageIcon(imagedir + imageFileNames[1]);

        icon2 = createImageIcon(imagedir + imageFileNames[0]);
        ImageIcon Iconguest = new ImageIcon(getScaledImage(icon2.getImage(), 33, 28));
        jButton5 = new JButton(Iconguest);
        //
        // jLabel1
        //
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel1.setText("Message:");
        //
        // jLabel2
        //
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel2.setText("Date:");
        //
        //jLabel3
        //
        jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel3.setForeground(new Color(0, 0, 255));
        jLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel3.setText("timing:");
        //
        //jLabel4
        //
        jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel4.setForeground(new Color(0, 0, 255));
        jLabel4.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel4.setText("hh:");
        //
        //jLabel5
        //
        jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel5.setForeground(new Color(0, 0, 255));
        jLabel5.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel5.setText("mm:");
        //
        //jLabel6
        //
        jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel6.setForeground(new Color(0, 0, 255));
        jLabel6.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel6.setText("To:");

        //
        //jTextField1
        //
        msg.setForeground(new Color(138, 43, 226));
        msg.setSelectedTextColor(new Color(0, 0, 255));
        msg.setToolTipText("Enter your massage");
        msg.setFont(new Font("Serif", Font.ITALIC, 18));
        JScrollPane scrollingArea = new JScrollPane(msg);
        scrollingArea.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        Container content = this.getContentPane();
        content.setLayout(new BorderLayout());

        //
        //date1
        //
        date.setForeground(new Color(0, 0, 255));
        date.setToolTipText("click on date1");
        date.setFont(new Font("Serif", Font.ITALIC, 18));
        date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);
            }

        });
        //
        //date2
        //
        hh.setForeground(new Color(0, 0, 255));
        hh.setToolTipText("click on date2");
        hh.setFont(new Font("Serif", Font.ITALIC, 18));
        hh.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                hh.getSelectedItem();
            }
        });

        mm.setForeground(new Color(0, 0, 255));
        mm.setToolTipText("click on date2");
        mm.setFont(new Font("Serif", Font.ITALIC, 18));
        mm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                mm.getSelectedItem();
            }
        });

        //
        //date2
        //
        receiptent.setForeground(new Color(0, 0, 255));
        receiptent.setToolTipText("click on date2");
        receiptent.setFont(new Font("Serif", Font.ITALIC, 18));
        receiptent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                receiptent.getSelectedItem();
            }
        });

        //
        // jButton1
        //
        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setForeground(new Color(0, 0, 255));
        jButton1.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton1.setText("Update");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }

        });
        //
        //jButton2
        //
        jButton2.setBackground(new Color(204, 204, 204));
        jButton2.setForeground(new Color(0, 0, 255));
        jButton2.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton2.setText("date1");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }

        });
        //
        //jButton3
        //
        jButton3.setBackground(new Color(204, 204, 204));
        jButton3.setForeground(new Color(0, 0, 255));
        jButton3.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton3.setText("send now");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }

        });
        //
        //
        //
        //jButton5
        //
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton5_actionPerformed(e);
            }

        });
        //
        // contentPane
        //

        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        //contentPane.setBackground(new Color(204, 204, 204));
        if (icon1 != null) {

            addComponent(contentPane, jButton5, 20, 300, 34, 28);
        }
        addComponent(contentPane, jLabel1, 5, 10, 116, 22);
        addComponent(contentPane, jLabel2, 5, 177, 97, 22);
        addComponent(contentPane, jLabel3, 5, 207, 97, 22);
        addComponent(contentPane, jLabel4, 110, 207, 97, 18);
        addComponent(contentPane, jLabel5, 180, 207, 97, 22);
        addComponent(contentPane, jLabel6, 5, 137, 97, 22);

        addComponent(contentPane, scrollingArea, 110, 10, 313, 100);
        addComponent(contentPane, date, 110, 177, 97, 22);
        addComponent(contentPane, hh, 130, 207, 47, 22);
        addComponent(contentPane, mm, 210, 207, 47, 22);
        addComponent(contentPane, receiptent, 110, 137, 57, 25);

        addComponent(contentPane, jButton2, 250, 177, 83, 28);
        addComponent(contentPane, jButton1, 230, 295, 113, 28);
        addComponent(contentPane, jButton3, 250, 137, 83, 28);

        //
        // login
        //
        this.setTitle("send alert message");
        this.setLocation(new Point(76, 182));
        add(contentPane, BorderLayout.CENTER);
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    /** Add Component Without a Layout Manager (Absolute Positioning) */
    private void addComponent(Container container, Component c, int x, int y, int width, int height) {
        c.setBounds(x, y, width, height);
        container.add(c);
    }

    //
    //
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    private void jTextField2_actionPerformed(ActionEvent e) {

    }

    //get nextdate in yyyymmdd format

    private void jButton1_actionPerformed(ActionEvent e) {
        System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
        //String message = new String(msg.getText());

        String message = new String(msg.getText());
        String str = (String)receiptent.getSelectedItem();
        String tempdate = new String(date.getText());
        String[] tempdate1;
        String delimiter = "-";
        tempdate1 = tempdate.split(delimiter, 3);
        int dd1 = Integer.parseInt(tempdate1[0]);
        int mm1 = Integer.parseInt(tempdate1[1]);
        int yyyy1 = Integer.parseInt(tempdate1[2]);
        int startdate = yyyy1 * 10000 + mm1 * 100 + dd1;
        String timehh = (String)hh.getSelectedItem();
        int timeh = Integer.parseInt(timehh);
        String timemm = (String)mm.getSelectedItem();
        int timem = Integer.parseInt(timemm);
        int time = timeh * 100 + timem;
        //String str1 = (String)receiptent.getSelectedItem();
        if (message.equals("")) // If message is empty > Do this >>>
        {
            jButton3.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>You must enter a message to send.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            msg.setText("");
            jButton3.setEnabled(true);
            this.setVisible(true);
        } else {
            if (str.equals("all")) {
                System.out.println(str);
                try {
                    // create our mysql database connection
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    String myUrl = "jdbc:mysql://localhost/nowsms";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root", "");
                    // our SQL SELECT query. 
                    // if you only need a few columns, specify them by name instead of using "*"
                    String query = "SELECT sender FROM draft";

                    // create the java statement
                    Statement st = conn.createStatement(); // execute the query, and get a java resultset
                    ResultSet rs = st.executeQuery(query);

                    // iterate through the java resultset

                    // our SQL SELECT query. 
                    // iterate through the java resultset
                    while (rs.next()) {
                        System.out.println("hello");
                        String mob_no = rs.getString("sender");
                        //int value = Integer.parseInt(mob_no);
                        System.out.println(message);
                        Statement st1 = conn.createStatement();
                        st1.executeUpdate("insert into alert(mobile, msg, date, time) values('" + mob_no + "', '"
                                + message + "', '" + startdate + "', '" + time + "')");
                        System.out.println("1 row affected");

                    }

                } catch (Exception ae) {
                    System.err.println("Got an exception! ");
                    System.err.println(ae.getMessage());
                }
            }
            if (str.equals("one")) {
                String mob_no = JOptionPane.showInputDialog("enter 10 digit mobile no");

                Pattern p = Pattern.compile("[A-Z,a-z, ,&%$#@!()*^]");
                Matcher m = p.matcher(mob_no);
                int flag = 0;
                if (m.find()) {
                    jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please enter only numbers");
                    jButton3.setEnabled(true);
                    this.setVisible(true);
                    flag = 1;

                }
                if (!(mob_no.length() < 11 && mob_no.length() > 9)) {
                    jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please enter valid mobile_no");
                    jButton3.setEnabled(true);
                    this.setVisible(true);
                    flag = 1;

                }
                if (flag == 0) {

                    try {
                        // create our mysql database connection
                        String myDriver = "org.gjt.mm.mysql.Driver";
                        String myUrl = "jdbc:mysql://localhost/nowsms";
                        Class.forName(myDriver);
                        Connection conn = DriverManager.getConnection(myUrl, "root", "");

                        // create the java statement

                        //int value = Integer.parseInt(mob_no);
                        System.out.println(message);
                        Statement st = conn.createStatement();

                        System.out.println(mob_no);
                        st.executeUpdate("insert into alert(mobile, msg, date, time) values('" + mob_no + "', '"
                                + message + "', '" + startdate + "', '" + time + "')");
                        System.out.println("1 row affected");

                    } catch (Exception ae) {
                        System.err.println("Got an exception! ");
                        System.err.println(ae.getMessage());
                    }

                    jButton3.setEnabled(true);
                    jButton1.setEnabled(true);
                    this.setVisible(true);
                }
            }
        }

    }

    private void jButton2_actionPerformed(ActionEvent e) {
        final JFrame f = new JFrame();
        //String p=new DatePicker(f).setPickedDate();
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                date.setText(new DatePicker(f).setPickedDate());
            }
        });

        //System.out.println(p);

    }

    private void jButton3_actionPerformed(ActionEvent e) {
        String message = new String(msg.getText());
        String str = (String)receiptent.getSelectedItem();
        if (message.equals("")) // If message is empty > Do this >>>
        {
            jButton3.setEnabled(false);
            JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>You must enter a message to send.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            msg.setText("");
            jButton3.setEnabled(true);
            this.setVisible(true);
        } else {
            if (str.equals("all")) {
                System.out.println(str);
                try {
                    // create our mysql database connection
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    String myUrl = "jdbc:mysql://localhost/nowsms";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root", "");
                    // our SQL SELECT query. 
                    // if you only need a few columns, specify them by name instead of using "*"
                    String query = "SELECT sender FROM draft";

                    // create the java statement
                    Statement st = conn.createStatement(); // execute the query, and get a java resultset
                    ResultSet rs = st.executeQuery(query);

                    // iterate through the java resultset

                    // our SQL SELECT query. 
                    // iterate through the java resultset
                    while (rs.next()) {
                        System.out.println("hello");
                        String mob_no = rs.getString("sender");
                        //int value = Integer.parseInt(mob_no);
                        System.out.println(message);
                        Statement st1 = conn.createStatement();
                        st1.executeUpdate("insert into outbox(receiver, replysms) values('" + mob_no + "', '" + message
                                + "')");
                        System.out.println("1 row affected");

                    }

                } catch (Exception ae) {
                    System.err.println("Got an exception! ");
                    System.err.println(ae.getMessage());
                }
            }
            if (str.equals("one")) {
                String mob_no = JOptionPane.showInputDialog("enter 10 digit mobile no");

                Pattern p = Pattern.compile("[A-Z,a-z, ,&%$#@!()*^]");
                Matcher m = p.matcher(mob_no);
                int flag = 0;
                if (m.find()) {
                    JOptionPane.showMessageDialog(null, "Please enter only numbers");
                    flag = 1;

                }
                if (!(mob_no.length() < 11 && mob_no.length() > 9)) {
                    JOptionPane.showMessageDialog(null, "Please enter valid mobile_no");
                    flag = 1;

                }
                if (flag == 0) {
                    try {
                        // create our mysql database connection
                        String myDriver = "org.gjt.mm.mysql.Driver";
                        String myUrl = "jdbc:mysql://localhost/nowsms";
                        Class.forName(myDriver);
                        Connection conn = DriverManager.getConnection(myUrl, "root", "");

                        // create the java statement

                        //int value = Integer.parseInt(mob_no);
                        System.out.println(message);
                        Statement st = conn.createStatement();
                        st.executeUpdate("insert into outbox(receiver, replysms) values('" + mob_no + "', '" + message
                                + "')");
                        System.out.println("1 row affected");

                    } catch (Exception ae) {
                        System.err.println("Got an exception! ");
                        System.err.println(ae.getMessage());
                    }
                }

                jButton3.setEnabled(true);
                this.setVisible(true);

            }
        }

    }

    //
    //
    private void jButton5_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
        updateshedule updateshedule = new updateshedule();
        System.out.println(updateshedule.hashCode());

    }
    //
    //

    //public static void main(String[] args)
    //{
    //JFrame.setDefaultLookAndFeelDecorated(true);
    ///JDialog.setDefaultLookAndFeelDecorated(true);
    //try
    //{
    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    //}
    //catch (Exception ex)
    //{
    //System.out.println("Failed loading L&F: ");
    //System.out.println(ex);
    //}
    //new msgalert();
    //};
}
