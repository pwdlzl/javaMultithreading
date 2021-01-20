package basic;

/**
 * @author Admin
 */
public class TestThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("thread run... " + i);
        }
    }

    public static void main(String[] args) {
        // create thread
        TestThread testThread = new TestThread();
        // start method will simultaneously run with main
        testThread.start();
        // run method will not simultaneously
//        testThread.run();

        for (int i = 0; i < 10000; i++) {
            System.out.println("main run... " + i);
        }
    }
}
