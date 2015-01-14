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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class updateshedule extends JFrame {
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
    private JSuggestField jTextField1;
    private JSuggestField jTextField2;
    private JTextField price1;
    private JTextField price2;
    private JTextField price3;
    private JTextField price4;
    private JTextField date1;
    private JTextField date2;
    private JTextField ticket;
    private JCheckBox A;
    private JCheckBox E;
    private JCheckBox M;
    private JCheckBox N;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton5;
    private JPanel contentPane;
    private String imagedir = "image/";
    private String[] imageFileNames = {"nexticon.png", "tinka.jpg"};
    private ImageIcon icon1, icon2;
    private static Vector<String> sample1;
    private static Vector<String> sample2;

    // End of variables declaration

    public updateshedule() {

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
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        sample1 = new Vector<String>();
        sample2 = new Vector<String>();
        try {
            // create our mysql database connection to our database nowsms
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/nowsms";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            Statement sttemp1 = conn.createStatement();
            Statement sttemp2 = conn.createStatement();
            ResultSet rs1 = sttemp1.executeQuery("SELECT DISTINCT theatre FROM movie ");
            ResultSet rs2 = sttemp2.executeQuery("SELECT DISTINCT movie FROM movie ");
            while (rs1.next()) {
                String hint = rs1.getString("theatre");

                sample1.add(hint);
            }
            while (rs2.next()) {
                String hint = rs2.getString("movie");

                sample2.add(hint);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        JFrame f = new JFrame();
        jTextField1 = new JSuggestField(f, sample1);
        jTextField2 = new JSuggestField(f, sample2);
        //jTextField1.setPreferredSuggestSize(d);

        price1 = new JTextField("100");
        price2 = new JTextField("100");
        price3 = new JTextField("100");
        price4 = new JTextField("100");
        date1 = new JTextField("18-10-2013");
        date2 = new JTextField("30-10-2012");
        ticket = new JTextField("50");
        A = new JCheckBox("A");
        E = new JCheckBox("E");
        M = new JCheckBox("M");
        N = new JCheckBox("N");
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        //  new JButton();
        jButton5 = new JButton();
        String image = "/image/11.jpg";
        contentPane = new ImagePanel(image);
        //contentPane = (JPanel)this.getContentPane();
        //
        //
        //
        //icon1 admin
        //

        icon1 = createImageIcon(imagedir + imageFileNames[1]);
        //    ImageIcon Iconadmin = new ImageIcon(getScaledImage(icon1.getImage(), 120, 120));
        //   new JButton(Iconadmin);

        icon2 = createImageIcon(imagedir + imageFileNames[0]);
        ImageIcon Iconguest = new ImageIcon(getScaledImage(icon2.getImage(), 23, 18));
        jButton5 = new JButton(Iconguest);

        //
        // jLabel1
        //
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel1.setText("Theatre:");
        //
        // jLabel2
        //
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel2.setText("Movie:");
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
        jLabel4.setText("price:");
        //
        //jLabel5
        //
        jLabel5.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel5.setForeground(new Color(0, 0, 255));
        jLabel5.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel5.setText("date:");
        //
        //jLabel6
        //
        jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel6.setForeground(new Color(0, 0, 255));
        jLabel6.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel6.setText("From:");
        //
        //jLabel7
        //
        jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel7.setForeground(new Color(0, 0, 255));
        jLabel7.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel7.setText("To:");
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
        A.setSelected(true);
        E.setSelected(true);
        M.setSelected(true);
        N.setSelected(true);
        //
        // price1
        //
        price1.setForeground(new Color(138, 43, 226));
        price1.setSelectedTextColor(new Color(138, 43, 226));
        price1.setFont(new Font("Serif", Font.ITALIC, 18));
        price1.setToolTipText("enter price");
        price1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1_actionPerformed(e);
            }

        });
        //
        //jTextField  price2
        //
        price2.setForeground(new Color(138, 43, 226));
        price2.setSelectedTextColor(new Color(138, 43, 226));
        price2.setFont(new Font("Serif", Font.ITALIC, 18));
        price2.setToolTipText("enter price");
        price2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1_actionPerformed(e);
            }

        });
        //
        //jTextField price3
        //
        price3.setForeground(new Color(138, 43, 226));
        price3.setSelectedTextColor(new Color(138, 43, 226));
        price3.setFont(new Font("Serif", Font.ITALIC, 18));
        price3.setToolTipText("enter price");
        price3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1_actionPerformed(e);
            }

        });
        //
        //jTextField price4
        //
        price4.setForeground(new Color(138, 43, 226));
        price4.setSelectedTextColor(new Color(0, 0, 255));
        price4.setFont(new Font("Serif", Font.ITALIC, 18));
        price4.setToolTipText("enter price");
        price4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1_actionPerformed(e);
            }

        });
        //
        //jTextField1
        //
        //jTextField1.setForeground(new Color(0, 0, 255));
        jTextField1.setSelectedTextColor(new Color(0, 255, 255));
        jTextField1.setForeground(new Color(255, 20, 147));
        jTextField1.setFont(new Font("Serif", Font.ITALIC, 15));
        jTextField1.setToolTipText("Enter theatre name");
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String text = textField.getText();
                if (text.contains(" ")) {
                    JLabel errorFields = new JLabel(
                            "<HTML><FONT COLOR = Blue>Space not allowed.Please try as RajMandir</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, errorFields);
                    // JOptionPane.
                    textField.setText("");
                    //this.setVisible(true);
                    //textField.setText(text.toUpperCase());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        //
        // jTextField2
        //
        jTextField2.setForeground(new Color(255, 20, 147));
        jTextField2.setSelectedTextColor(new Color(255, 20, 147));
        jTextField2.setFont(new Font("Serif", Font.ITALIC, 15));
        jTextField2.setToolTipText("Enter movie");
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String text = textField.getText();
                if (text.contains(" ")) {
                    JLabel errorFields = new JLabel(
                            "<HTML><FONT COLOR = Blue>Space not allowed.Please try as BadmashCompany</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, errorFields);
                    jTextField2.setText("");
                    //this.setVisible(true);
                    //textField.setText(text.toUpperCase());
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        //
        //date1
        //
        date1.setForeground(new Color(255, 20, 147));
        date1.setFont(new Font("Serif", Font.ITALIC, 18));
        date1.setToolTipText("click on date1");
        date1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);
            }

        });
        //
        //date2
        //
        date2.setForeground(new Color(255, 20, 147));
        date2.setFont(new Font("Serif", Font.ITALIC, 18));
        date2.setToolTipText("click on date2");
        date2.addActionListener(new ActionListener() {
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
        ticket.setToolTipText("enter available tickets");
        ticket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField2_actionPerformed(e);
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
        jButton3.setText("date2");
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
        addComponent(contentPane, jLabel2, 5, 47, 97, 18);
        addComponent(contentPane, jLabel3, 5, 77, 97, 18);
        addComponent(contentPane, jLabel4, 5, 107, 97, 18);
        addComponent(contentPane, jLabel5, 5, 137, 97, 18);
        addComponent(contentPane, jLabel6, 55, 137, 97, 18);
        addComponent(contentPane, jLabel7, 76, 177, 97, 18);
        addComponent(contentPane, jLabel8, 5, 207, 97, 18);
        addComponent(contentPane, A, 110, 77, 37, 18);
        addComponent(contentPane, E, 160, 77, 37, 18);
        addComponent(contentPane, M, 210, 77, 37, 18);
        addComponent(contentPane, N, 270, 77, 37, 18);
        addComponent(contentPane, jTextField1, 110, 10, 258, 22);
        addComponent(contentPane, jTextField2, 110, 45, 258, 22);
        addComponent(contentPane, price1, 110, 107, 37, 22);
        addComponent(contentPane, price2, 160, 107, 37, 22);
        addComponent(contentPane, price3, 210, 107, 37, 22);
        addComponent(contentPane, price4, 270, 107, 37, 22);
        addComponent(contentPane, date1, 110, 137, 97, 22);
        addComponent(contentPane, date2, 110, 177, 97, 22);
        addComponent(contentPane, ticket, 110, 207, 87, 22);
        addComponent(contentPane, jButton1, 250, 295, 113, 28);
        addComponent(contentPane, jButton2, 250, 137, 83, 28);
        addComponent(contentPane, jButton3, 250, 177, 83, 28);
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

    private void jTextField1_actionPerformed(ActionEvent e) {

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

    private void jButton1_actionPerformed(ActionEvent e) {
        System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
        String theatrename = new String(jTextField1.getText());
        String moviename = new String(jTextField2.getText());
        int update = 0;
        if (theatrename.equals("") || moviename.equals("")) // If theatre and moviename is empty > Do this >>>
        {
            jButton1.setEnabled(false);
            JLabel errorFields = new JLabel(
                    "<HTML><FONT COLOR = Blue>You must enter a theatrename and moviename to update.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            jTextField1.setText("");
            jTextField2.setText("");
            jButton1.setEnabled(true);
            this.setVisible(true);
        } else {
            JLabel optionLabel = new JLabel("<HTML><FONT COLOR = Blue>You entered</FONT><FONT COLOR = RED> <B>"
                    + theatrename + "</B></FONT> <FONT COLOR = Blue>as CinemaHall.<BR> Is this correct?</FONT></HTML>");
            int confirm = JOptionPane.showConfirmDialog(null, optionLabel);
            switch (confirm) { // Switch > Case
                case JOptionPane.YES_OPTION: // Attempt to Login user
                    jButton1.setEnabled(false);// Set button enable to false to prevent 2 update attempts
                    update = 1;
                    break;

                case JOptionPane.NO_OPTION: // No Case.(Go back. Set text to 0)
                    jButton1.setEnabled(false);
                    jTextField1.setText("");
                    jTextField2.setText("");
                    jButton1.setEnabled(true);
                    break;

                case JOptionPane.CANCEL_OPTION: // Cancel Case.(Go back. Set text to 0)
                    jButton1.setEnabled(false);
                    jTextField1.setText("");
                    jTextField2.setText("");
                    jButton1.setEnabled(true);
                    break;

            } // End Switch > Case

        }

        String price_1 = new String(price1.getText());
        int value1 = Integer.parseInt(price_1);
        String price_2 = new String(price2.getText());
        int value2 = Integer.parseInt(price_2);
        String price_3 = new String(price3.getText());
        int value3 = Integer.parseInt(price_3);
        String price_4 = new String(price4.getText());
        int value4 = Integer.parseInt(price_4);
        String date_1 = new String(date1.getText());
        String date_2 = new String(date2.getText());
        String avail_ticket = new String(ticket.getText());
        int tickets = Integer.parseInt(avail_ticket);
        int flag[] = {0, 0, 0, 0};
        if (A.isSelected() || E.isSelected() || M.isSelected() || N.isSelected()) {
            if (A.isSelected()) {
                flag[0] = 1;
            }
            if (E.isSelected()) {
                flag[1] = 1;
            }
            if (M.isSelected()) {
                flag[2] = 1;
            }
            if (N.isSelected()) {
                flag[3] = 1;
            }

        } else {
            System.out.println("hi");
        }
        System.out.println("" + date_1 + "");
        String[] tempdate1;
        String delimiter = "-";
        tempdate1 = date_1.split(delimiter, 3);
        int dd1 = Integer.parseInt(tempdate1[0]);
        int mm1 = Integer.parseInt(tempdate1[1]);
        int yyyy1 = Integer.parseInt(tempdate1[2]);
        int startdate = yyyy1 * 10000 + mm1 * 100 + dd1;
        String[] tempdate2;
        String delimeter = "-";
        tempdate2 = date_2.split(delimeter, 3);
        int dd2 = Integer.parseInt(tempdate2[0]);
        int mm2 = Integer.parseInt(tempdate2[1]);
        int yyyy2 = Integer.parseInt(tempdate2[2]);
        int enddate = yyyy2 * 10000 + mm2 * 100 + dd2;
        //System.out.println(tempdate2[1]);
        if (update == 1) {

            try {
                // create our mysql database connection
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/nowsms";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "");

                // our SQL SELECT query. 
                for (int i = startdate; i <= enddate; i = nextdate(i)) {
                    System.out.println("\n" + i);

                    if (flag[0] == 1) {
                        String strtiming = "A";
                        //System.out.println(""+theatrename+moviename+strtiming+value1+tickets+i+"");
                        Statement st = conn.createStatement();
                        st.executeUpdate("insert into movie(theatre, movie ,timing ,price ,ticket, date) values('"
                                + theatrename + "', '" + moviename + "', '" + strtiming + "', '" + value1 + "', '"
                                + tickets + "', '" + i + "')");
                        System.out.println("1 row affected");
                    }
                    if (flag[1] == 1) {
                        String strtiming = "E";
                        //System.out.println(""+theatrename+moviename+strtiming+value1+tickets+i+"");
                        Statement st = conn.createStatement();
                        st.executeUpdate("insert into movie(theatre, movie ,timing ,price ,ticket, date) values('"
                                + theatrename + "', '" + moviename + "', '" + strtiming + "', '" + value2 + "', '"
                                + tickets + "', '" + i + "')");
                        System.out.println("1 row affected");
                    }
                    if (flag[2] == 1) {
                        String strtiming = "M";
                        //System.out.println(""+theatrename+moviename+strtiming+value1+tickets+i+"");
                        Statement st = conn.createStatement();
                        st.executeUpdate("insert into movie(theatre, movie ,timing ,price ,ticket, date) values('"
                                + theatrename + "', '" + moviename + "', '" + strtiming + "', '" + value3 + "', '"
                                + tickets + "', '" + i + "')");
                        System.out.println("1 row affected");
                    }
                    if (flag[3] == 1) {
                        String strtiming = "N";
                        //System.out.println(""+theatrename+moviename+strtiming+value1+tickets+i+"");
                        Statement st = conn.createStatement();
                        st.executeUpdate("insert into movie(theatre, movie ,timing ,price ,ticket, date) values('"
                                + theatrename + "', '" + moviename + "', '" + strtiming + "', '" + value4 + "', '"
                                + tickets + "', '" + i + "')");
                        System.out.println("1 row affected");
                    }
                }
            } catch (Exception ae) {
                System.err.println("Got an exception! ");
                System.err.println(ae.getMessage());
                jButton1.setEnabled(false);
                JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>" + "Got an exception!" + ae.getMessage()
                        + "</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);
                jTextField1.setText("");
                jTextField2.setText("");
                jButton1.setEnabled(true);
                this.setVisible(true);
            }

        }
        jButton1.setEnabled(true);

    }

    private void jButton2_actionPerformed(ActionEvent e) {
        final JFrame f = new JFrame();
        //String p=new DatePicker(f).setPickedDate();
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                date1.setText(new DatePicker(f).setPickedDate());
            }
        });

        //System.out.println(p);

    }

    private void jButton3_actionPerformed(ActionEvent e) {
        final JFrame f = new JFrame();
        //String p=new DatePicker(f).setPickedDate();
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                date2.setText(new DatePicker(f).setPickedDate());
            }
        });

        //System.out.println(p);

    }

    private void jButton5_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
        msgalert msgalert = new msgalert();
        System.out.println(msgalert);
    }

    /*public static void main(String[] args)
    {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    try
    {
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }
    catch (Exception ex)
    {
    System.out.println("Failed loading L&F: ");
    System.out.println(ex);
    }
    new updateshedule();
    };*/
}
