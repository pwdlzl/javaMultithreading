package basic;

public class TestLambda {
    // static inner class
    static class Lik implements ILike{
        @Override
        public void lambda() {
            System.out.println("inner test lambda......");
        }
    }
    public static void main(String[] args) {
//        basic.ILike iLike = new basic.Like();
//        iLike.lambda();
        // static inner class
//        basic.ILike like = new Lik();
//        like.lambda();
        // part inner class
//        class Li implements basic.ILike{
//            @Override
//            public void lambda() {
//                System.out.println("part inner class test lambda......");
//            }
//        }
//
//        basic.ILike li = new Li();
//        li.lambda();
        // none name inner class
        ILike l = new ILike() {
            @Override
            public void lambda() {
                System.out.println("none name inner class test lambda......");
            }
        };

        l.lambda();

        // lambda
        ILike ll = () -> {
            System.out.println("lambda......");
        };
        ll.lambda();
    }
}

interface ILike{
    void lambda();
}

class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println("test lambda......");
    }
}
