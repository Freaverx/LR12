package example5;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class example5 {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String args) {
        int array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);
        MaxElementInArrayParallelTask task = new MaxElementInArrayParallelTask(array, 0, array.length);
        int result = pool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Максимальный элемент массива: " + result);
        System.out.println("Время выполнения: " + (endTime - startTime) + " ms");
    }

    private static class MaxElementInArrayParallelTask extends RecursiveTask<Integer> {
        private int array;
        private int start;
        private int end;

        MaxElementInArrayParallelTask(int array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < 10) {
                return Arrays.stream(array, start, end).max().getAsInt();
            } else {
                int middle = start + (end - start) / 2;
                MaxElementInArrayParallelTask leftTask = new MaxElementInArrayParallelTask(array, start, middle);
                MaxElementInArrayParallelTask rightTask = new MaxElementInArrayParallelTask(array, middle, end);
                leftTask.fork();
                return Math.max(leftTask.join(), rightTask.compute());
            }
        }
    }
}