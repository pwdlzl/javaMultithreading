package basic;

public class StaticProxy {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("start....")).start();
        WeddingCompany weddingCompany = new WeddingCompany(new You());
        weddingCompany.HappyWedding();
    }
}

interface Wedding{
    void HappyWedding();

}

class You implements Wedding {

    @Override
    public void HappyWedding() {
        System.out.println("happy wedding");
    }
}

// proxy
class WeddingCompany implements Wedding {
    private final Wedding target;

    public WeddingCompany(Wedding target) {
        this.target = target;
    }

    @Override
    public void HappyWedding() {
        before();
        this.target.HappyWedding();
        after();
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }
}
