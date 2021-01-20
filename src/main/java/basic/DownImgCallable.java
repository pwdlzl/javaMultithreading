package basic;

import java.util.concurrent.*;

public class DownImgCallable implements Callable<Boolean> {
    private final String url;
    private final String filePath;

    public DownImgCallable(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    @Override
    public Boolean call() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, filePath);
        System.out.println("下载文件: " + filePath);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DownImgCallable downImgCallable = new DownImgCallable("http://p.baidu.com/question/06206162633338663666611300", "1.jpg");

        // create service
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Boolean> future1 = executorService.submit(downImgCallable);

        boolean result = future1.get();

        System.out.println("result: " + result);

        // shut down service
        executorService.shutdown();
    }
}
