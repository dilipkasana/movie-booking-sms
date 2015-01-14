public class chck implements Runnable {
    static boolean b;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {

            b = check.checkCheck(setup.port_name, setup.br, setup.db, setup.sb, setup.pb);
            Thread.sleep(4000);

        } catch (InterruptedException e) {

        }
    }

}
