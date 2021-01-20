package singleThreadExecution;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Log {
    public static void println(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}

// 资源个数有限
class BoundedResource {
    private final static Random random = new Random(314159);
    private final Semaphore semaphore;
    private final int permits;

    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    // 使用资源
    public void use() throws InterruptedException {
        semaphore.acquire();
        try {
            doUse();
        } finally {
            semaphore.release();
        }
    }

    // 实际使用资源
    protected void doUse() throws InterruptedException {
        Log.println("BEGIN: used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("END:   used = " + (permits - semaphore.availablePermits()));
    }

}

// 使用资源的线程
class UserUseThread extends Thread {
    public static final Random random = new Random(26535);
    private final BoundedResource resource;

    public UserUseThread(BoundedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class SemaphoreMain {
    public static void main(String[] args) {
        // 设置 3 个资源
        BoundedResource resource = new BoundedResource(3);

        // 10 个线程使用资源
        for (int i = 0; i < 10; i++) {
            new UserUseThread(resource).start();
        }
    }
}
