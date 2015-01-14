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
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class guest extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @param args
     */
    // Variables declaration
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private String imagedir = "image/";
    private String[] imageFileNames = {"nexticon.png", "guest2.jpg", "sunw03.jpg", "sunw04.jpg", "sunw05.jpg"};
    private JPanel contentPane;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private ImageIcon icon1, icon2;
    private Random rand;
    int num;

    public guest() {

        super();
        creat();
        this.setVisible(true);
    }

    private void creat() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        String image = "/image/10.jpg";
        contentPane = new ImagePanel(image);
        //rand=new Random();
        //int num = rand.nextInt(10000);
        //
        //icon1 admin
        //

        icon1 = createImageIcon(imagedir + imageFileNames[1]);
        ImageIcon Iconadmin = new ImageIcon(getScaledImage(icon1.getImage(), 120, 120));
        jButton1 = new JButton(Iconadmin);
        //
        //icon2 go next
        //

        icon2 = createImageIcon(imagedir + imageFileNames[0]);
        ImageIcon Iconguest = new ImageIcon(getScaledImage(icon2.getImage(), 23, 18));
        jButton2 = new JButton(Iconguest);

        //
        // jLabel1
        //
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 0, 255));
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel1.setText("guest");
        //
        // jLabel2
        //
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(0, 0, 255));
        jLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel2.setText("mobile no");
        //
        //
        //
        //
        // jLabel3
        //
        jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel3.setForeground(new Color(0, 0, 255));
        jLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
        jLabel3.setText("verification code");
        //
        //
        //
        //jTextField1
        //
        jTextField1.setForeground(new Color(0, 0, 255));
        jTextField1.setSelectedTextColor(new Color(0, 0, 255));
        jTextField1.setToolTipText("Enter 10 digit mobile no");
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String mob_no = textField.getText();
                Pattern p = Pattern.compile("[A-Z,a-z, ,&%$#@!()*^]");
                Matcher m = p.matcher(mob_no);
                if (m.find()) {
                    jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please enter only numbers");
                    jTextField1.setText("");
                    jButton3.setEnabled(true);

                }

                //JPasswordField passwordField = (JPasswordField) e.getSource();
                // String text = passwordField.getText();
                // Pattern p = Pattern.compile("[A-Z,a-z, ,+.?&%$#@!()*^]");
                // Matcher m = p.matcher(text);
                //if (m.find()) {
                //  JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>enter date of birth in dd/mm/yyyy</FONT></HTML>");
                //  JOptionPane.showMessageDialog(null,errorFields);
                //  jTextField2.setText("");
                //this.setVisible(true);
                //textField.setText(text.toUpperCase());
                //  }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

        //
        //
        //
        //jTextField1
        //
        jTextField2.setForeground(new Color(0, 0, 255));
        jTextField2.setSelectedTextColor(new Color(0, 0, 255));
        jTextField2.setToolTipText("Enter verification code");
        jTextField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String code = textField.getText();
                Pattern p = Pattern.compile("[A-Z,a-z, ,&%$#@!()*^]");
                Matcher m = p.matcher(code);
                if (m.find()) {
                    jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please enter only numbers");
                    jTextField2.setText("");
                    jButton3.setEnabled(true);

                }

                //JPasswordField passwordField = (JPasswordField) e.getSource();
                // String text = passwordField.getText();
                // Pattern p = Pattern.compile("[A-Z,a-z, ,+.?&%$#@!()*^]");
                // Matcher m = p.matcher(text);
                //if (m.find()) {
                //  JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>enter date of birth in dd/mm/yyyy</FONT></HTML>");
                //  JOptionPane.showMessageDialog(null,errorFields);
                //  jTextField2.setText("");
                //this.setVisible(true);
                //textField.setText(text.toUpperCase());
                //  }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });

        //
        //
        //jButton1.setBackground(new Color(204, 204, 204));
        //jButton1.setForeground(new Color(0, 0, 255));
        //jButton1.setText("Update");
        //jButton1.addActionListener(new ActionListener() {
        //public void actionPerformed(ActionEvent e)
        //{
        //jButton1_actionPerformed(e);
        //}

        //});
        //
        //jButton2
        //
        //jButton2.setBackground(new Color(204, 204, 204));
        //jButton2.setForeground(new Color(0, 0, 255));
        //jButton2.setText("switch user");
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
        jButton3.setForeground(new Color(255, 0, 255));
        jButton3.setText("switch user");
        jButton3.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }

        });

        //
        //
        //jButton3
        //
        jButton4.setBackground(new Color(204, 204, 204));
        jButton4.setForeground(new Color(0, 0, 255));
        jButton4.setText("get code");
        jButton4.setFont(new Font("Serif", Font.ITALIC, 18));
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }

        });

        //
        //
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        contentPane.setBackground(new Color(204, 204, 204));

        if (icon1 != null) {

            addComponent(contentPane, jButton1, 230, 50, 130, 130);
        }
        if (icon2 != null) {

            addComponent(contentPane, jButton2, 400, 273, 24, 18);
        }
        //loadimages.execute();
        try {
            Thread.sleep(0);

        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        addComponent(contentPane, jLabel1, 270, 185, 106, 28);
        addComponent(contentPane, jLabel2, 100, 220, 106, 28);
        addComponent(contentPane, jLabel3, 100, 270, 146, 28);
        addComponent(contentPane, jTextField1, 230, 220, 130, 22);
        addComponent(contentPane, jTextField2, 230, 270, 130, 22);
        addComponent(contentPane, jButton3, 230, 320, 130, 28);
        addComponent(contentPane, jButton4, 380, 220, 100, 25);
        //addComponent(contentPane, jLabel2, 290,215,97,18);
        this.setTitle("Login as guest");
        this.setLocation(new Point(76, 182));
        add(contentPane, BorderLayout.CENTER);
        this.setSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        //loadimages.execute();
    }

    private void addComponent(Container container, Component c, int x, int y, int width, int height) {
        c.setBounds(x, y, width, height);
        container.add(c);
    }

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

    // private void jButton1_actionPerformed(ActionEvent e)
    // {
    //this.setVisible(false);
    //this.dispose();
    // }
    private void jButton2_actionPerformed(ActionEvent e) {
        System.out.println("hihihi");
        String code = jTextField2.getText();
        //String message = new String(msg.getText());
        //String str = (String)receiptent.getSelectedItem();
        if (code.equals("")) // If message is empty > Do this >>>
        {
            jButton2.setEnabled(false);
            JLabel errorFields = new JLabel(
                    "<HTML><FONT COLOR = Blue>You must enter verification code to process.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            jTextField2.setText("");
            jButton2.setEnabled(true);
            this.setVisible(true);
        } else {
            if (num == Integer.parseInt(code)) {
                this.setVisible(false);
                this.dispose();
                updateshedule updateshedule = new updateshedule();
                System.out.println(updateshedule.hashCode());
            } else {
                jButton2.setEnabled(false);
                JLabel errorFields = new JLabel(
                        "<HTML><FONT COLOR = Blue>This verification code is INVALID.</FONT></HTML>");
                JOptionPane.showMessageDialog(null, errorFields);
                jTextField2.setText("");
                jButton2.setEnabled(true);
                this.setVisible(true);
            }
        }
    }

    private void jButton3_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
        start.i = 1;
        start start = new start();
        System.out.println(start.hashCode());

    }

    private void jButton4_actionPerformed(ActionEvent e) {
        String mob_no = jTextField1.getText();
        int flag = 0;
        if (!(mob_no.length() < 11 && mob_no.length() > 9)) {
            jButton3.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Please enter valid 10 digit mobile_no");
            //jTextField2.setText("");
            jButton3.setEnabled(true);
            //this.setVisible(true);
            flag = 1;

        }

        if (flag == 0) {
            rand = new Random();
            num = rand.nextInt(10000);
            //System.out.println(""+num+"");
            try {
                // create our mysql database connection
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/nowsms";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "");

                // create the java statement

                //int value = Integer.parseInt(mob_no);
                //System.out.println(message);
                mob_no = "+91" + mob_no;
                String msg = "your verification code for login is:" + num;
                Statement st = conn.createStatement();
                st.executeUpdate("insert into outbox(receiver, replysms) values('" + mob_no + "', '" + msg + "')");
                System.out.println("1 row affected");

            } catch (Exception ae) {
                System.err.println("Got an exception! ");
                System.err.println(ae.getMessage());
            }

        }

    }

    /*//public static void main(String[] args)
    //{
    //JFrame.setDefaultLookAndFeelDecorated(true);
    //JDialog.setDefaultLookAndFeelDecorated(true);
    //try
    //{
    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    //}
    //catch (Exception ex)
    //{
    //System.out.println("Failed loading L&F: ");
    //System.out.println(ex);
    //}
    ////new guest();
    //};*/
}
