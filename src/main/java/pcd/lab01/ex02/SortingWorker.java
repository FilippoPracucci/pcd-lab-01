package pcd.lab01.ex02;

import java.util.Arrays;

public class SortingWorker extends AbstractWorker {

    final int[] array;
    final int rangeStart;
    final int rangeEnd;

    public SortingWorker(final String name, final int[] array, final int startIndex, final int endIndex) {
        super(name);
        this.array = array;
        this.rangeStart = startIndex;
        this.rangeEnd = endIndex;
    }

    @Override
    public void run() {
        log("started - sorting from " + this.rangeStart + " to " + this.rangeEnd);
        Arrays.sort(this.array, this.rangeStart, this.rangeEnd);
        log("completed");
    }
}
