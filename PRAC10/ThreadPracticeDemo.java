
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SharedBuffer {
    private final int[] buffer = new int[3];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    synchronized void produce(int value) throws InterruptedException {
        while (count == buffer.length)
            wait();

        buffer[in] = value;
        in = (in + 1) % buffer.length;
        count++;

        System.out.println("Produced: " + value);
        notifyAll();
    }

    synchronized int consume() throws InterruptedException {
        while (count == 0)
            wait();

        int value = buffer[out];
        out = (out + 1) % buffer.length;
        count--;

        System.out.println("Consumed: " + value);
        notifyAll();

        return value;
    }
}

public class ThreadPracticeDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1. THREAD POOL");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            final int id = i;

            pool.submit(() -> {
                System.out.println("Task " + id + " running on "
                        + Thread.currentThread().getName());

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\n2. PRODUCER-CONSUMER");

        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    buffer.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    buffer.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
    }
}
