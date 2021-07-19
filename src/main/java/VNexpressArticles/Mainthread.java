package VNexpressArticles;

import VNexpressArticles.MultiThread.MyThread;
import VNexpressArticles.controller.ArticleController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Mainthread {
    public static void main(String[] args) {
        Elements urlArticles;
        long startTime = Calendar.getInstance().getTimeInMillis();
        List<MyThread> listThreads = new ArrayList<>();
        //ket noi toi rang vnexpress va lay toan do link bai viet trong the a
        try {
            Document doc = Jsoup.connect("https://vnexpress.net/khoa-hoc").get();
            urlArticles = doc.select(".title-news a");
            for (int i = 0; i < urlArticles.size(); i++) {
                String url = urlArticles.get(i).attr("href");
                //khoi tao thread cho moi i, va add cac thread nay vao 1 list de quan ly
                MyThread myThread = new MyThread(url);
                listThreads.add(myThread);
                myThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listThreads.size(); i++) {
            try {
                listThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < listThreads.size(); i++) {
            ArticleController articleController = new ArticleController();
            articleController.insertArticlesCollection(listThreads.get(i).getArticle());
            System.out.println(i + 1 + " " + listThreads.get(i).getArticle().getTitle());
        }
        long endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("save all data complete !!!");
        System.out.println(endTime - startTime + " mls Working time");
    }
}
