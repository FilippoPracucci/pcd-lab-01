package pcd.lab01.ex02;

public class MergingWorkerTwoParts extends AbstractWorker {

    final int[] array;
    final SortingWorker worker1;
    final SortingWorker worker2;

    public MergingWorkerTwoParts(final String name, final int[] array, final SortingWorker worker1, final SortingWorker worker2) {
        super(name);
        this.array = array;
        this.worker1 = worker1;
        this.worker2 = worker2;
    }

    @Override
    public void run() {
        log("start merging");
        log("wait two subpart sorting");
        try {
            final long t0 = System.currentTimeMillis();
            this.worker1.join();
            this.worker2.join();
            log("Two subpart sorted");
            final int[] merged = this.merge(this.array);
            for (int i = 0; i < merged.length; i++) {
                this.array[i] = merged[i];
            }
            final long t1 = System.currentTimeMillis();
            log("completed -- " + (t1 - t0) + " ms for merging");
        } catch (final InterruptedException e) {
            log("InterruptedException");
        }
    }

    private int[] merge(final int[] array) {
        final int[] newArray = new int[array.length];
        int indexFirstPart = 0;
        final int maxIndexFirstPart = array.length / 2;
        int indexSecondPart = maxIndexFirstPart;
        final int maxIndexSecondPart = array.length;
        int indexNewArray = 0;

        while ((indexFirstPart < maxIndexFirstPart) && (indexSecondPart < maxIndexSecondPart)) {
            if (array[indexFirstPart] <= array[indexSecondPart]) {
                newArray[indexNewArray] = array[indexFirstPart];
                indexFirstPart++;
            } else {
                newArray[indexNewArray] = array[indexSecondPart];
                indexSecondPart++;
            }
            indexNewArray++;
        }
        if (indexFirstPart < maxIndexFirstPart) {
            while (indexFirstPart < maxIndexFirstPart) {
                newArray[indexNewArray] = array[indexFirstPart];
                indexFirstPart++;
                indexNewArray++;
            }
        } else {
            while (indexSecondPart < maxIndexSecondPart) {
                newArray[indexNewArray] = array[indexSecondPart];
                indexSecondPart++;
                indexNewArray++;
            }
        }

        return newArray;
    }
}
