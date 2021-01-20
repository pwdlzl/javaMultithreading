package basic;

import java.util.concurrent.locks.ReentrantLock;

public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "tom").start();
        new Thread(station, "lily").start();
        new Thread(station, "me").start();
    }

}

class BuyTicket implements Runnable {
    boolean flag = true;
    private int ticketNumbers = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buy() throws InterruptedException {
        try {
            lock.lock();
            if (ticketNumbers <= 0) {
                flag = false;
                return;
            }

            // sleep
            Thread.sleep(100);

            System.out.println(Thread.currentThread().getName() + " get " + ticketNumbers--);
        }finally {
            lock.unlock();
        }

    }
}
