package basic;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("///////");
        });

        // check state
        Thread.State state = thread.getState();
        System.out.println(state);

        // run
        thread.start();
        state = thread.getState();
        System.out.println(state);

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }


    }
}
