package basic;

public class ThreadStop implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run....... Thread" + i++);
        }
    }

    public void stopThread() {
        this.flag = false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();

        new Thread(threadStop).start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main " + i);
            if (i==90) {
                threadStop.stopThread();
                System.out.println("thread stop!");
            }
        }
    }
}
