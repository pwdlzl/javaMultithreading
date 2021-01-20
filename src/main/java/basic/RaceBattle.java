package basic;

public class RaceBattle implements Runnable{
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {
            // rabbit relax
            if ("兔子".equals(Thread.currentThread().getName()) && (i % 10 == 0)) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "--> 跑了" + i + "步");
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is: " + winner);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        RaceBattle raceBattle = new RaceBattle();

        new Thread(raceBattle, "兔子").start();
        new Thread(raceBattle, "乌龟").start();
    }
}
