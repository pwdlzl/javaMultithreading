package basic;

public class DeadLock {
    public static void main(String[] args) {
        Makeup makeup = new Makeup(0, "one");
        Makeup makeup1 = new Makeup(1, "two");

        new Thread(makeup).start();
        new Thread(makeup1).start();

    }
}

class LipStick{

}

class Mirror{

}

class Makeup implements Runnable{
    static final LipStick lipStick = new LipStick();
    static final Mirror mirror = new Mirror();

    int person;
    String personName;

    Makeup(int person, String personName) {
        this.person = person;
        this.personName = personName;
    }

    @Override
    public void run() {
        try {
            makeUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeUp() throws InterruptedException {
        if (this.person == 0) {
            synchronized (lipStick) {
                // get lipStick
                System.out.println(this.personName + " get lip stick");
                Thread.sleep(1000);

                synchronized (mirror) {
                    System.out.println(this.personName + " get mirror");
                }
            }
        } else {
            synchronized (mirror) {
                // get lipStick
                System.out.println(this.personName + " get mirror");
                Thread.sleep(2000);

                synchronized (lipStick) {
                    System.out.println(this.personName + " get lip stick");
                }
            }
        }
    }
}
