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
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class start extends JFrame {

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
    private JLabel jLabel4;
    private String imagedir = "image/";
    private String[] imageFileNames = {"guest2.jpg", "tinka.jpg"};
    private JPanel contentPane;
    private JButton jButton1;
    private JButton jButton2;
    private ImageIcon icon1, icon2;
    static int i = 0;

    //static Player helloJMFPlayer = null;

    public start() {

        super();
        creat();
        this.setVisible(true);
        if (i == 0) {

            //mylove helloJMF = new mylove();

        }

    }

    private void creat() {
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        String image = "/image/2.jpg";
        contentPane = new ImagePanel(image);
        //
        //icon1 admin
        //

        icon1 = createImageIcon(imagedir + imageFileNames[1]);
        ImageIcon Iconadmin = new ImageIcon(getScaledImage(icon1.getImage(), 80, 80));
        jButton1 = new JButton(Iconadmin);
        //
        //icon2 guest
        //

        icon2 = createImageIcon(imagedir + imageFileNames[0]);
        ImageIcon Iconguest = new ImageIcon(getScaledImage(icon2.getImage(), 80, 80));
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
        jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel2.setForeground(new Color(138, 43, 226));
        jLabel2.setText("Guest");
        jLabel2.setFont(new Font("Serif", Font.ITALIC, 18));
        //
        //
        // jLabel4
        //
        jLabel4.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel4.setForeground(new Color(255, 20, 147));
        jLabel4.setText("In ur loving memories....");
        jLabel4.setFont(new Font("Serif", Font.ITALIC, 18));
        //
        //
        // jLabel3
        //
        jLabel3.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel3.setForeground(new Color(138, 43, 226));
        jLabel3.setText("U will be in my breath forever......");
        jLabel3.setFont(new Font("Serif", Font.ITALIC, 18));
        //
        //jButton1.setBackground(new Color(204, 204, 204));
        //jButton1.setForeground(new Color(0, 0, 255));
        //jButton1.setText("Update");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }

        });
        //
        //jButton2
        //
        //jButton2.setBackground(new Color(204, 204, 204));
        //jButton2.setForeground(new Color(0, 0, 255));
        //jButton2.setText("date1");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }

        });

        //
        //
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEtchedBorder());
        contentPane.setBackground(new Color(204, 204, 204));

        if (icon1 != null) {

            addComponent(contentPane, jButton1, 150, 130, 85, 85);
        }
        if (icon2 != null) {

            addComponent(contentPane, jButton2, 270, 130, 85, 85);
        }
        //loadimages.execute();
        try {
            Thread.sleep(0);

        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
        addComponent(contentPane, jLabel1, 165, 225, 126, 25);
        addComponent(contentPane, jLabel2, 290, 225, 126, 25);
        addComponent(contentPane, jLabel3, 0, 0, 240, 25);
        addComponent(contentPane, jLabel4, 390, 330, 226, 25);
        this.setTitle("Authentication requird");
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

    private void jButton1_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
        Admin admin = new Admin();
        System.out.println(admin.hashCode());

    }

    private void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
        this.dispose();
        guest guest = new guest();
        System.out.println(guest.hashCode());
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
        start start = new start();
        System.out.println(start.hashCode());
    }
}
