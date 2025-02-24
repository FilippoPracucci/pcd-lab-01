package pcd.lab01.ex01;

import java.util.Random;

import static org.fusesource.jansi.Ansi.*;

public class WordThread extends Thread {

    private final Screen screen;
    private final int x0;
    private final int y0;
    private final String word;
    private final int millisecondsToWait;

    public WordThread(final String name, final Screen screen, final String word, final int y0, final int x0) {
        super(name);
        this.screen = screen;
        this.word = word;
        this.x0 = x0;
        this.y0 = y0;
        this.millisecondsToWait = new Random().nextInt(500);
    }

    public void run() {
        int i;
        for (i = 0; i < 10; i++) {
            screen.writeStringAt(y0 + i, x0, Color.GREEN, this.word);
            try {
                Thread.sleep(this.millisecondsToWait);
            } catch (Exception ex) {
            }
            screen.writeStringAt(y0 + i, x0, Color.BLACK, this.word);
        }
        screen.writeStringAt(y0 + i, x0, Color.GREEN, this.word);
    }
}
