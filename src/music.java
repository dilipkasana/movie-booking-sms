import java.io.File;

import javax.media.Manager;
import javax.media.Player;

public class music {
    //JFrame frame = new JFrame(" Hello JMF Player");
    static Player helloJMFPlayer = null;

    @SuppressWarnings({"deprecation"})
    public music() {
        try { // method using URL
              //URL url=new URL("file",null,"teriyadoon.mp3");
            String curDir = System.getProperty("user.dir");
            System.out.println(curDir);
            //File file=new File("src/music/wada.mp3");
            File file = new File("." + "/src/music/wada.mp3");
            helloJMFPlayer = Manager.createRealizedPlayer(file.toURL());
            helloJMFPlayer.getControlPanelComponent();
            helloJMFPlayer.start();
        } catch (Exception e) {
            System.out.println(" Unable to create the audioPlayer :" + e);
        }
        //frame.getContentPane().add( control, BorderLayout.CENTER);
        //frame.addWindowListener( new WindowAdapter() {
        //public void windowClosing(WindowEvent we) {
        //mylove.stop();
        //System.exit(0);
        //}
        //});
        //frame.pack();
        //frame.setSize( new Dimension(200,50) );
        //frame.setVisible(true);
        //System.out.println(System.getProperty ("user.dir"));  
        System.out.println(".k.k.k.k.k.k.k.k");

    }

    //public static void stop(){
    //helloJMFPlayer.stop();
    //helloJMFPlayer.close();
    //}
    public static void main(String args[]) {
        music music = new music();
        System.out.println(music.hashCode());

    }
}
