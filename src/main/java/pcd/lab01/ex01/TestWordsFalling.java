package pcd.lab01.ex01;

import java.util.List;
import static pcd.lab01.ex01.AuxLib.*;

public class TestWordsFalling {

    private static final int START_LINE = 0;
    private static final int END_LINE = 10;

    public static void main(String[] args) {

        final String sentence = "This is a simple sentence with words ready to fall";

        List<WordPos> wordList = getWordsPos(sentence);

        for (WordPos wordPos: wordList) {
            final int x0 = wordPos.pos();
            Thread wordThread = new WordThread(wordPos.word() + "-WordThread", wordPos.word(), START_LINE, x0, END_LINE);
            wordThread.start();
        }
    }
}
