package pcd.lab01.ex01;

import java.util.Random;

import static org.fusesource.jansi.Ansi.*;

public class WordThread extends Thread {

    private final int startPos;
    private final int startLine;
    private final int endLine;
    private final String word;
    private final int millisecondsToWait;

    public WordThread(final String name, final String word, final int startLine, final int startPos, final int endLine) {
        super(name);
        this.word = word;
        this.startPos = startPos;
        this.startLine = startLine;
        this.endLine = endLine;
        this.millisecondsToWait = new Random().nextInt(500);
    }

    public void run() {
        final Screen screen = Screen.getInstance();
        screen.clear();
        int i;
        for (i = 0; i < this.endLine; i++) {
            screen.writeStringAt(startLine + i, startPos, Color.GREEN, this.word);
            try {
                Thread.sleep(this.millisecondsToWait);
            } catch (Exception ex) {
            }
            screen.writeStringAt(startLine + i, startPos, Color.BLACK, this.word);
        }
        screen.writeStringAt(startLine + i, startPos, Color.GREEN, this.word);
    }
}
