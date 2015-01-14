import javax.comm.CommDriver;

/**
 * Copyright (c) 2013 Drishti-Soft Solutions Pvt. Ltd.
 *
 * @author: DILIP
 * Date:  Aug 20, 2013
 */

/**
 *
 */
public class Dependencies {
    private Dependencies() {

    }

    public static void checkDependencies() {
        String myDriver = "com.sun.comm.Win32Driver";
        try {
            CommDriver cd = (CommDriver)Class.forName("com.sun.comm.Win32Driver").newInstance();

            cd.initialize();
        } catch (Exception e) {
            System.out.println("Error occured Cannot find " + myDriver);
            System.exit(1);
        }
    }
}
