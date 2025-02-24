package pcd.lab01.ex01;

import java.util.List;
import static pcd.lab01.ex01.AuxLib.*;

public class TestWordsFalling {

    private static final int INITIAL_Y_VALUE = 0;

    public static void main(String[] args) {

        final Screen screen = Screen.getInstance();
        screen.clear();

        final String sentence = "This is a simple sentence with words ready to fall";

        List<WordPos> wordList = getWordsPos(sentence);

        for (WordPos wordPos: wordList) {
            final int x0 = wordPos.pos();
            Thread wordThread = new WordThread(wordPos.word() + "-WordThread", screen, wordPos.word(), INITIAL_Y_VALUE, x0);
            wordThread.start();
        }
    }
}
