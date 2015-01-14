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
import java.sql.Statement;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class setup extends JFrame {
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
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JTextArea msg;
    private JComboBox Port_Name;
    private JComboBox Baud_Rate;
    private JComboBox Data_Bits;
    private JComboBox Stop_Bits;
    private JComboBox Parity_Bits;
    private JTextField num;
    static JTextField status;
    private JTextField ticket;
    private JCheckBox A;
    private JCheckBox E;
    private JCheckBox M;
    private JCheckBox N;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton5;
    private JPanel contentPane;
    private String imagedir = "image/";
    private String[] imageFileNames = {"nexticon.png", "tinka.jpg"};
    private ImageIcon icon1, icon2;
    int count = 0;
    static int connection = 0;
    static int total_msg = 0;
    static int flag = 0;
    static String port_name = "COM4";
    static int br = 921600;
    static int db = 8;
    static int sb = 1;
    static int pb = 0;
    boolean connection_bug = false;

    // End of variables declaration

    public setup() {

        super();
        Dependencies.checkDependencies();
        create();
        //ListPorts l=new ListPorts();
        this.setVisible(true);
        messaging.getInstance();

    }

    private void create() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        msg = new JTextArea();
        String port[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                "", ""};
        int i = 0;
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier portt = (CommPortIdentifier)ports.nextElement();
            String type;
            switch (portt.getPortType()) {
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
            port[i] = portt.getName();
            System.out.println(port[i] + ": " + type);

            i++;
        }

        /*
         * String[] names = SerialPortLocal.getPortList();
         System.out.println("Serial ports reported by system:");
         for (int i = 0; i < names.length; i++) {
          System.out.println(names[i]);
         }
         */
        Port_Name = new JComboBox(port);
        String rate[] = {"921600", "460800", "230400", "115200", "57600", "38400", "19200", "9600", "4800", "2400",
                "1200", "300"};
        Baud_Rate = new JComboBox(rate);
        String data[] = {"8", "7", "6", "5", "4", "3", "2", "1", "0"};
        Data_Bits = new JComboBox(data);
        String stop[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Stop_Bits = new JComboBox(stop);
        String parity[] = {"0", "1", "2"};
        Parity_Bits = new JComboBox(parity);
        num = new JTextField("+919413102070");
        status = new JTextField("Please test2time");
        status.setEditable(false);
        ticket = new JTextField("50");
        A = new JCheckBox("A");
        E = new JCheckBox("E");
        M = new JCheckBox("M");
        N = new JCheckBox("N");
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton5 = new JButton();
        String image = imagedir + "/11.jpg";
        contentPane = new ImagePanel(image);
        //contentPane = (JPanel)this.getContentPane();
        //
        //
        //
        //icon1 admin
        //

        icon1 = createImageIcon(imagedir + imageFileNames[1]);
        icon2 = createImageIcon(imagedir + imageFileNames[0]);
        ImageIcon Iconguest = new ImageIcon(getScaledImage(icon2.getImage(), 23, 18));
        jButton5 = new JButton(Iconguest);

        //
        // jLabel1
        //
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel1.setText("Port_Name");
        //
        // jLabel2
        //
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel2.setText("Baud_Rate");
        //
        //jLabel3
        //
        jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel3.setForeground(new Color(0, 0, 255));
        jLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel3.setText("Data_Bits");
        //
        //jLabel4
        //
        jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel4.setForeground(new Color(0, 0, 255));
        jLabel4.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel4.setText("Stop_Bits");
        //
        //jLabel5
        //
        jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel5.setForeground(new Color(0, 0, 255));
        jLabel5.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel5.setText("Parity_Bits");
        //
        //jLabel6
        //
        jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel6.setForeground(new Color(0, 0, 255));
        jLabel6.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel6.setText("mob_no:");
        //
        //jLabel7
        //
        jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel7.setForeground(new Color(0, 0, 255));
        jLabel7.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel7.setText("Type Your Msg Here!!!");
        //
        //jLabel8
        //
        jLabel8.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel8.setForeground(new Color(0, 0, 255));
        jLabel8.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel8.setText("Tickets:");
        //
        //Checkbox
        //
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
        A.setSelected(true);
        E.setSelected(true);
        M.setSelected(true);
        N.setSelected(true);
        //
        //date2
        //
        Port_Name.setForeground(new Color(0, 0, 255));
        Port_Name.setToolTipText("select");
        Port_Name.setFont(new Font("Serif", Font.ITALIC, 18));
        Port_Name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                Port_Name.getSelectedItem();
            }
        });

        Baud_Rate.setForeground(new Color(0, 0, 255));
        Baud_Rate.setToolTipText("select");
        Baud_Rate.setFont(new Font("Serif", Font.ITALIC, 18));
        Baud_Rate.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                Baud_Rate.getSelectedItem();
            }
        });
        //
        // price1
        //
        Data_Bits.setForeground(new Color(0, 0, 255));
        Data_Bits.setToolTipText("select");
        Data_Bits.setFont(new Font("Serif", Font.ITALIC, 18));
        Data_Bits.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                Data_Bits.getSelectedItem();
            }
        });
        //
        // price1
        //
        Stop_Bits.setForeground(new Color(0, 0, 255));
        Stop_Bits.setToolTipText("select");
        Stop_Bits.setFont(new Font("Serif", Font.ITALIC, 18));
        Stop_Bits.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                Stop_Bits.getSelectedItem();
            }
        });
        //
        // price1
        //
        Parity_Bits.setForeground(new Color(0, 0, 255));
        Parity_Bits.setToolTipText("select");
        Parity_Bits.setFont(new Font("Serif", Font.ITALIC, 18));
        Parity_Bits.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                Parity_Bits.getSelectedItem();
            }
        });

        //
        //date1
        //
        num.setForeground(new Color(255, 20, 147));
        num.setFont(new Font("Serif", Font.ITALIC, 18));
        num.setToolTipText("Enter 10 digit no. with prefix +91");
        num.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);
            }

        });
        //
        //date2
        //
        status.setForeground(new Color(255, 20, 147));
        status.setFont(new Font("Serif", Font.ITALIC, 18));
        status.setToolTipText("please test first");
        status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);
            }

        });
        //
        //date2
        //
        ticket.setForeground(new Color(255, 20, 147));
        ticket.setFont(new Font("Serif", Font.ITALIC, 18));
        ticket.setToolTipText("select");
        ticket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);

            }

        });
        //
        // jButton1
        //

        //
        //jButton2
        //
        jButton2.setBackground(new Color(204, 204, 204));
        jButton2.setForeground(new Color(0, 0, 255));
        jButton2.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton2.setText("test");
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
        jButton3.setText("send");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }

        });
        //
        //jButton5
        //
        jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton5_actionPerformed(e);
            }

        });

        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        //contentPane.setBackground(new Color(204, 204, 204));
        if (icon1 != null) {

            addComponent(contentPane, jButton5, 500, 300, 24, 18);
        }
        addComponent(contentPane, jLabel1, 5, 10, 106, 18);
        addComponent(contentPane, jLabel2, 5, 45, 97, 18);
        addComponent(contentPane, jLabel3, 5, 80, 97, 18);
        addComponent(contentPane, jLabel4, 5, 115, 97, 18);
        addComponent(contentPane, jLabel5, 5, 150, 97, 18);
        addComponent(contentPane, scrollingArea, 250, 40, 313, 100);
        addComponent(contentPane, jLabel6, 250, 157, 97, 18);
        addComponent(contentPane, jLabel7, 250, 5, 187, 25);
        //addComponent(contentPane, jLabel8, 5,207,97,18);
        //addComponent(contentPane,A, 110,77,37,18);
        //addComponent(contentPane,E, 160,77,37,18);
        //addComponent(contentPane,M, 210,77,37,18);
        //addComponent(contentPane,N, 270,77,37,18);
        addComponent(contentPane, Port_Name, 110, 10, 100, 22);
        addComponent(contentPane, Baud_Rate, 110, 45, 100, 22);
        addComponent(contentPane, Data_Bits, 110, 80, 100, 22);
        addComponent(contentPane, Stop_Bits, 110, 115, 100, 22);
        addComponent(contentPane, Parity_Bits, 110, 150, 100, 22);
        //addComponent(contentPane, xxx, 110,185,37,22);
        addComponent(contentPane, num, 320, 157, 127, 22);
        addComponent(contentPane, status, 200, 267, 157, 25);
        //addComponent(contentPane, ticket, 110,207,87,22);
        //addComponent(contentPane, jButton1, 340,157,113,28);
        addComponent(contentPane, jButton2, 100, 267, 83, 28);
        addComponent(contentPane, jButton3, 460, 157, 83, 22);
        //
        // login
        //
        this.setTitle("Update Movie Shedule");
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

    int nextdate(int inputdate) {
        int dd = inputdate % 100;
        int yyyy = inputdate / 10000;
        int mm = (inputdate - yyyy * 10000) / 100;
        int ddmax = 31, mmmax = 12;
        if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
            ddmax = 31;
        }
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            ddmax = 30;
        }
        if (mm == 2) {
            if (yyyy % 100 == 0) {
                if (yyyy % 400 == 0) {
                    ddmax = 29;
                }
            } else if (yyyy % 4 == 0) {
                ddmax = 29;
            } else {
                ddmax = 28;
            }
        }
        if (dd < ddmax) {
            dd = dd + 1;
        } else if (dd == ddmax) {
            dd = 01;
            if (mm < mmmax) {
                mm = mm + 1;
            } else if (mm == mmmax) {
                mm = 01;
                yyyy = yyyy + 1;
            }
        }
        return (yyyy * 10000 + mm * 100 + dd);

    }

    @SuppressWarnings("deprecation")
    private void jButton2_actionPerformed(ActionEvent e) {

        /*if(check.flag==1 || check.flag==2) {
        	check.sp.close();
        	check.flag=0;
        }*/
        //final JFrame f = new JFrame();
        //String p=new DatePicker(f).setPickedDate();
        System.out.println("\njButton2_actionPerformed(ActionEvent e) called.");
        port_name = (String)Port_Name.getSelectedItem();
        String baud_rate = (String)Baud_Rate.getSelectedItem();
        br = Integer.parseInt(baud_rate);
        String databits = (String)Data_Bits.getSelectedItem();
        db = Integer.parseInt(databits);
        String stop_bits = (String)Stop_Bits.getSelectedItem();
        sb = Integer.parseInt(stop_bits);
        String parity_bits = (String)Parity_Bits.getSelectedItem();
        pb = Integer.parseInt(parity_bits);

        if (connection != 0 || receive.flag != 0 || /*check.flag!=0 || */send.flag != 0) {
            System.out.println("running can't be tested");
            jButton2.disable();

        } else {
            //boolean b=check.check(port_name,br,db,sb,pb);

            long startTime = System.currentTimeMillis();
            Thread t = new Thread(new chck());
            t.start();
            System.out.println("Waiting for checking to finish");
            while (t.isAlive()) {
                System.out.println("Still waiting...");
                try {
                    t.join(1000);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (((System.currentTimeMillis() - startTime) > 5000) && t.isAlive()) {

                    System.out.println("Tired of waiting");
                    System.exit(1);

                    t.stop();

                    t.interrupt();
                    try {
                        t.join();
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (!chck.b) {
                    String error = "<HTML><FONT COLOR = Blue>" + "Port may be in use" + "\n"
                            + "<FONT COLOR = Red>Port found" + "</FONT></HTML>";
                    JLabel errorFields = new JLabel(error);
                    JOptionPane.showMessageDialog(null, errorFields);
                }
            }
            System.out.println("Finally!");

            System.out.println(status);
        }

        count++;
        //sendsms.sendsms("love love love love love love","+918104405668");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //if(check.status!=6)status.setText("disconnected");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (check.status == 6) {
                    connection = 1;
                    status.setEditable(true);
                    status.setText("connected");
                    status.setEditable(false);
                    System.out.println("\nfinish");
                }

            }
        });
    }

    //System.out.println(p);

    private void jButton3_actionPerformed(ActionEvent e) {
        if (count < 1) {
            jButton3.setEnabled(false);
            JLabel errorFields = new JLabel(
                    "<HTML><FONT COLOR = Blue>please test at least two time to continue</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);

            jButton3.setEnabled(true);
            this.setVisible(true);
        }
        if (count >= 1) {

            String mssg = new String(msg.getText());
            String no = new String(num.getText());
            if (no.length() == 13 && no.substring(0, 3).equals("+91")) {
                //final JFrame f = new JFrame();
                //String p=new DatePicker(f).setPickedDate();
                jButton5.setEnabled(false);
                try {
                    // create our mysql database connection to our database nowsms
                    String myDriver = "org.gjt.mm.mysql.Driver";
                    String myUrl = "jdbc:mysql://localhost/nowsms";
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(myUrl, "root", "");
                    Statement st = conn.createStatement();
                    st.executeUpdate("insert into outbox( receiver, replysms) values('" + no + "', '" + mssg + "')");
                    System.out.println("1 row affected");
                    st.close();
                } catch (Exception ee) {
                    // TODO Auto-generated catch block
                    System.err.println("Got an exception! ");
                    System.err.println(ee.getMessage());
                }
                jButton5.setEnabled(true);
            } else {
                jButton3.setEnabled(false);
                JLabel errorFields = new JLabel(
                        "<HTML><FONT COLOR = Blue>please enter mobile no. in +919988776655 format</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);

                jButton3.setEnabled(true);
                this.setVisible(true);
            }
        }
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //status.setText(new DatePicker(f).setPickedDate());
            }
        });

        //System.out.println(p);

    }

    private void jButton5_actionPerformed(ActionEvent e) {
        if (count < 1) {
            jButton3.setEnabled(false);
            JLabel optionLabel = new JLabel(
                    "<HTML><FONT COLOR = Blue>you are not connected.going forward may unstable ur system.Do u want to force????</FONT></HTML>");
            int confirm = JOptionPane.showConfirmDialog(null, optionLabel);
            switch (confirm) { // Switch > Case
                case JOptionPane.YES_OPTION: // Attempt to Login user
                    jButton3.setEnabled(false);// Set button enable to false to prevent 2 update attempts
                    this.setVisible(false);
                    this.dispose();
                    start start = new start();
                    System.out.println(start.hashCode());
                    break;

                case JOptionPane.NO_OPTION: // No Case.(Go back. Set text to 0)
                    jButton3.setEnabled(false);
                    jButton3.setEnabled(true);
                    break;

                case JOptionPane.CANCEL_OPTION: // Cancel Case.(Go back. Set text to 0)
                    jButton3.setEnabled(false);
                    jButton3.setEnabled(true);
                    break;
            }
        } else {
            this.setVisible(false);
            this.dispose();
            start start = new start();
            System.out.println(start);
        }

    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            System.out.println("Failed loading L&F: ");
            System.out.println(ex);
        }
        setup setup = new setup();
        System.out.println(setup);
    }
}
