
class Counter {
    int count = 0;

    void increment() {
        count++;
    }

    synchronized void safeIncrement() {
        count++;
    }
}

class SeatBooking {
    int seatsLeft = 5;
    int booked = 0;

    void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            seatsLeft--;
            booked++;
        }
    }

    synchronized void safeBook() {
        if (seatsLeft > 0) {
            seatsLeft--;
            booked++;
        }
    }
}

public class ThreadRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread[] t = new Thread[10];

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++)
                    c.increment();
            });
            t[i].start();
        }

        for (Thread x : t)
            x.join();

        System.out.println("Counter without synchronization: " + c.count);

        c.count = 0;

        for (int i = 0; i < 10; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++)
                    c.safeIncrement();
            });
            t[i].start();
        }

        for (Thread x : t)
            x.join();

        System.out.println("Counter with synchronization: " + c.count);

        SeatBooking s1 = new SeatBooking();
        Thread[] b = new Thread[10];

        for (int i = 0; i < 10; i++) {
            b[i] = new Thread(s1::book);
            b[i].start();
        }

        for (Thread x : b)
            x.join();

        System.out.println("Seats booked without synchronization: " + s1.booked);

        SeatBooking s2 = new SeatBooking();

        for (int i = 0; i < 10; i++) {
            b[i] = new Thread(s2::safeBook);
            b[i].start();
        }

        for (Thread x : b)
            x.join();

        System.out.println("Seats booked with synchronization: " + s2.booked);
        System.out.println("Seats remaining: " + s2.seatsLeft);
    }
}
