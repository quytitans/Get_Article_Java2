package VNexpressArticles.MultiThread;

import VNexpressArticles.unity.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MyThread extends Thread {
    private String url;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private int status;

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
            Element titleElement = articleDetails.selectFirst("h1.title-detail");
            if (titleElement != null) {
                title = titleElement.text();
            }
            Element descriptionElement = articleDetails.selectFirst("p.description");
            if (descriptionElement != null) {
                description = descriptionElement.text();
            }
            Element contentElement = articleDetails.selectFirst("p.description");
            if (contentElement != null) {
                content = contentElement.text();
            }
            Element thumbnailElement = articleDetails.selectFirst("div.fig-picture picture img");
            if (thumbnailElement != null) {
                thumbnail = thumbnailElement.attr("data-src");
            }
            status = 1;
            article = new Article(url, title, description, content, thumbnail, status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
