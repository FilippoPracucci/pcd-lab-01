package pcd.lab01.ex02;

public class AbstractWorker extends Thread {

    public AbstractWorker(final String name) {
        super(name);
    }

    public void log(final String message) {
        System.out.println("[" + this.getName() + "][ " + System.currentTimeMillis() + " ] " + message);
    }
}
