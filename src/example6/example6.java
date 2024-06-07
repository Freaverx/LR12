package example6;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class example6 {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String args[]) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);
        SumArrayElementsParallelTask task = new SumArrayElementsParallelTask(array, 0, array.length);
        long result = pool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Сумма элементов массива: " + result);
        System.out.println("Время выполнения: " + (endTime - startTime) + " ms");
    }

    private static class SumArrayElementsParallelTask extends RecursiveTask<Long> {
        private int[] array;
        private int start;
        private int end;

        SumArrayElementsParallelTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start < 10) {
                return Arrays.stream(array, start, end).sum();
            } else {
                int middle = start + (end - start) / 2;
                SumArrayElementsParallelTask leftTask = new SumArrayElementsParallelTask(array, start, middle);
                SumArrayElementsParallelTask rightTask = new SumArrayElementsParallelTask(array, middle, end);
                leftTask.fork();
                return leftTask.join() + rightTask.compute();
            }
        }
    }
}
