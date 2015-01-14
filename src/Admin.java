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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Admin extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @param args
     */
    // Variables declaration
    private JLabel jLabel1;
    private String imagedir = "image/";
    private String[] imageFileNames = {"nexticon.png", "tinka.jpg"};
    private JPanel contentPane;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JPasswordField jPasswordField1;
    private ImageIcon icon1, icon2;

    public Admin() {

        super();
        creat();
        this.setVisible(true);
    }

    private void creat() {
        jLabel1 = new JLabel();
        jButton3 = new JButton();
        jPasswordField1 = new JPasswordField();
        String image = "/image/3.jpg";
        contentPane = new ImagePanel(image);
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
        jLabel1.setForeground(new Color(0, 255, 255));
        jLabel1.setText("Admin");
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        //
        // jLabel2
        //
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setForeground(new Color(0, 255, 255));
        jLabel1.setText("Admin");
        jLabel1.setFont(new Font("Serif", Font.ITALIC, 18));
        //
        //
        //
        //jTextField1
        //
        jPasswordField1.setForeground(new Color(0, 0, 255));
        jPasswordField1.setSelectedTextColor(new Color(0, 0, 255));
        jPasswordField1.setToolTipText("Enter DOB in dd/mm/yyyy");
        jPasswordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JPasswordField passwordField = (JPasswordField)e.getSource();
                @SuppressWarnings("deprecation")
                String text = passwordField.getText();
                Pattern p = Pattern.compile("[A-Z,a-z, ,+.?&%$#@!()*^]");
                Matcher m = p.matcher(text);
                if (m.find()) {
                    JLabel errorFields = new JLabel(
                            "<HTML><FONT COLOR = Blue>enter date of birth in dd/mm/yyyy</FONT></HTML>");
                    JOptionPane.showMessageDialog(null, errorFields);
                    jPasswordField1.setText("");
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
        jButton3.setForeground(new Color(0, 0, 255));
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
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        //contentPane.setBackground(new Color(204, 204, 204));

        if (icon1 != null) {

            addComponent(contentPane, jButton1, 230, 50, 130, 130);
        }
        if (icon2 != null) {

            addComponent(contentPane, jButton2, 400, 223, 24, 18);
        }
        //loadimages.execute();
        try {
            Thread.sleep(0);

        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        addComponent(contentPane, jLabel1, 270, 185, 106, 28);
        addComponent(contentPane, jPasswordField1, 230, 220, 130, 22);
        addComponent(contentPane, jButton3, 230, 270, 130, 28);
        //addComponent(contentPane, jLabel2, 290,215,97,18);
        this.setTitle("Login as Admin");
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
        System.out.println("kkkkkkkkkkkkk");
        @SuppressWarnings("deprecation")
        String code = jPasswordField1.getText();
        //String message = new String(msg.getText());
        //String str = (String)receiptent.getSelectedItem();
        if (!(code.equals("18-01-1991") || code.equals("18/01/1991"))) // If message is empty > Do this >>>
        {
            jButton2.setEnabled(false);
            JLabel errorFields = new JLabel(
                    "<HTML><FONT COLOR = Blue>enter ur dob in dd/mm/yyyy to process.</FONT></HTML>");
            JOptionPane.showMessageDialog(null, errorFields);
            jPasswordField1.setText("");
            jButton2.setEnabled(true);
            this.setVisible(true);
        } else {
            if (code.equals("18-01-1991") || code.equals("18/01/1991")) {
                this.setVisible(false);
                this.dispose();
                updateshedule updateshedule = new updateshedule();
                System.out.println(updateshedule.hashCode());
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
    //};*/
}
