package VNexpressArticles.MultiThread;

import VNexpressArticles.controller.ArticleController;
import VNexpressArticles.unity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MyThread extends Thread {
    private String url;

    public MyThread(String url) {
        this.url = url;
    }

    Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void run() {
        Document articleDetails = null;
        try {
            articleDetails = Jsoup.connect(url).get();
            String title = articleDetails.select(".sidebar-1 h1").first().text();
            String description = "update later";
            String content = "update later";
            String thumbnailUrl = "update later";
            int status = 1;
            article = new Article(url, title, description, content, thumbnailUrl, status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
