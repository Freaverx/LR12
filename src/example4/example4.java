package example4;

public class example4 {
    public static void main(String args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(i + 1)).start();
        }
    }
}
