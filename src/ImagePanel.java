import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /*the default image to use*/
    String imageFile = "/image/img0.jpg";

    public ImagePanel() {
        super();
    }

    public ImagePanel(String image) {
        super();
        this.imageFile = image;
    }

    public ImagePanel(LayoutManager layout) {
        super(layout);
    }

    @Override
    public void paintComponent(Graphics g) {
        /*create image icon to get image*/
        ImageIcon imageicon = new ImageIcon(getClass().getResource(imageFile));
        Image image = imageicon.getImage();

        /*Draw image on the panel*/
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
