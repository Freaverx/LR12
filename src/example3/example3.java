package example3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class example3 {
    public static void main(String args) {
        // Создаем два потока
        Runnable evenNumbers = () -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println(i);
            }
        };
        Runnable oddNumbers = () -> {
            for (int i = 1; i <= 10; i += 2) {
                System.out.println(i);
            }
        };
        // Запускаем потоки
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(evenNumbers);
        executor.execute(oddNumbers);
        // Ждем завершения работы потоков
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
