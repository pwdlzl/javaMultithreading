package basic;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        SyncContainer container = new SyncContainer();

        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

class Producer implements Runnable{
    SyncContainer container;

    public Producer(SyncContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("produce " + i + " chicken");
        }
    }
}

class Consumer implements Runnable{
    SyncContainer container;

    public Consumer(SyncContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Chicken chicken = container.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consume " + i + " chicken");
        }
    }
}

class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SyncContainer{
    Chicken[] chickens = new Chicken[10];
    int size = 0;
    // push chicken
    public synchronized void push(Chicken chicken) {
        // container full
        if (size == chickens.length) {
            // notify consumer
            this.notifyAll();
        }

        chickens[size++] = chicken;
        // notify consumer
        this.notifyAll();
    }

    // gen chicken
    public synchronized Chicken get() throws InterruptedException {
        // none chicken
        if (size == 0) {
            // wait producer
            this.wait();
        }

        // consume
        // notify
        Chicken chicken = chickens[size--];
        this.notifyAll();
        return chicken;
    }
}
