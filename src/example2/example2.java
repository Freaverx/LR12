package example2;

public class example2 {
    public static void main(String args) {
        Runnable task = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // Запускаем поток
        new Thread(task).start();
    }
}
