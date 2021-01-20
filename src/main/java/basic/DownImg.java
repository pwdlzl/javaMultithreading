package basic;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownImg extends Thread{
    private final String url;
    private final String filePath;

    public DownImg(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downLoader(url, filePath);
    }

    public static void main(String[] args) {
        DownImg downImg = new DownImg("http://p.baidu.com/question/06206162633338663666611300", "1.jpg");
        downImg.start();
    }


}


class WebDownLoader{
    public void downLoader(String url, String file) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(file));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IO 异常，downLoader 异常");
        }
    }
}
