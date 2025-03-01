package pcd.lab01.ex02;

import java.util.List;

public class MergingWorker extends AbstractWorker {

    final int[] array;
    final List<SortingWorker> workerList;

    public MergingWorker(final String name, final int[] array, final List<SortingWorker> workers) {
        super(name);
        this.array = array;
        this.workerList = workers;
    }

    @Override
    public void run() {
        log("started - merging " + this.workerList.size() + " parts");
        log("wait subpart sorting...");
        try {
            for (final SortingWorker worker: this.workerList) {
                worker.join();
            }
            log("subpart sorted, start merging...");

            final long startTime = System.currentTimeMillis();
            final int[] merged = this.merge(this.array, this.workerList.size());
            for (int i = 0; i < merged.length; i++) {
                this.array[i] = merged[i];
            }
            final long endTime = System.currentTimeMillis();
            log("completed -- " + (endTime - startTime) + " ms for merging");
        } catch (final InterruptedException e) {
            log("InterruptedException");
        }
    }

    private int[] merge(final int[] array, final int nParts) {
        final int[] newArray = new int[array.length];

        final int partSize = array.length / nParts;
        int rangeStart = 0;

        final int[] indexes = new int[nParts];
        final int[] maxIndexes = new int[nParts];
        for (int i = 0; i < indexes.length - 1; i++) {
            indexes[i] = rangeStart;
            maxIndexes[i] = rangeStart + partSize;
            rangeStart = maxIndexes[i];
        }
        indexes[indexes.length - 1] = rangeStart;
        maxIndexes[indexes.length - 1] = array.length;

        int indexNewArray = 0;
        boolean allFinished = false;
        while (!allFinished) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < indexes.length; i++) {
                if ((indexes[i] < maxIndexes[i]) && (array[indexes[i]] < min)) {
                    index = i;
                    min = array[indexes[i]];
                }
            }

            if (index != -1) {
                newArray[indexNewArray] = array[indexes[index]];
                indexes[index]++;
                indexNewArray++;
            } else {
                allFinished = true;
            }
        }

        return newArray;
    }
}
