package basic;

public class DownImgRunnable implements Runnable {
    private final String url;
    private final String filePath;

    public DownImgRunnable(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        DownImg downImg = new DownImg("http://p.baidu.com/question/06206162633338663666611300", "1.jpg");
        new Thread(downImg).start();
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, filePath);
    }
}

