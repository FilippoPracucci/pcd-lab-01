package pcd.lab01.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcurrentSort {

    final static int VECTOR_SIZE = 400_000_000;

    public static void main(String[] args) {

        log("Generating array...");
        final int[] array = genArray(VECTOR_SIZE);

        log("Array generated.");

        log("Creating workers to sort " + VECTOR_SIZE + " elements...");

        final int agentsNumber = Runtime.getRuntime().availableProcessors();
        final int jobSize = array.length / agentsNumber;
        int rangeStart = 0;
        int rangeEnd = jobSize - 1;

        final List<SortingWorker> workerList = new ArrayList<>();
        for (int i = 0; i < agentsNumber; i++) {
            final SortingWorker worker = new SortingWorker("worker-" + (i+1), array, rangeStart, rangeEnd);
            workerList.add(worker);
            rangeStart = rangeEnd + 1;
            rangeEnd += jobSize;
        }
        final MergingWorker mergingWorker = new MergingWorker("merging-worker", array, workerList);

        final long startTime = System.currentTimeMillis();
        for (final SortingWorker worker: workerList) {
            worker.start();
        }
        mergingWorker.start();

        try {
            mergingWorker.join();
            final long endTime = System.currentTimeMillis();
            log("Done. Time elapsed: " + (endTime - startTime) + " ms");
        } catch (InterruptedException e) {
            log("InterruptedException");
        }
    }

    private static int[] genArray(int n) {
        Random gen = new Random(System.currentTimeMillis());
        var v = new int[n];
        for (int i = 0; i < v.length; i++) {
            v[i] = gen.nextInt();
        }
        return v;
    }

    private static void dumpArray(int[] v) {
        for (var l:  v) {
            System.out.print(l + " ");
        }
        System.out.println();
    }

    private static void log(String msg) {
        System.out.println(msg);
    }

}
