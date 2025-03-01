package pcd.lab01.ex02;

import java.util.Random;

public class ConcurrentSortTwoThreads {

    final static int VECTOR_SIZE = 400_000_000;

    public static void main(String[] args) {

        log("Generating array...");
        final int[] array = genArray(VECTOR_SIZE);

        log("Array generated.");

        log("Creating workers to sort " + VECTOR_SIZE + " elements...");

        final int middleIndex =  array.length / 2;
        final SortingWorker worker1 = new SortingWorker("worker-1", array, 0, middleIndex - 1);
        final SortingWorker worker2 = new SortingWorker("worker-2", array, middleIndex, array.length - 1);
        final MergingWorkerTwoParts mergingWorker = new MergingWorkerTwoParts("merging-worker", array, worker1, worker2);

        final long startTime = System.currentTimeMillis();
        worker1.start();
        worker2.start();
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
