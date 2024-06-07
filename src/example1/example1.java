package example1;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class example1 {
    public static void main(String[] args) {
        // Создаем два потока
        Runnable task1 = () -> {
            while (true) {
                System.out.println("Поток 1: " + new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable task2 = () -> {
            while (true) {
                System.out.println("Поток 2: " + new Date());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // Запускаем потоки
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(task1, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(task2, 0, 1, TimeUnit.SECONDS);
        // Ждем 10 секунд
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Завершаем работу
        executor.shutdown();
    }
}
