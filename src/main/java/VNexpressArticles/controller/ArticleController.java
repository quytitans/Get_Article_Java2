package VNexpressArticles.controller;

import VNexpressArticles.model.ArticleModel;
import VNexpressArticles.unity.Article;

public class ArticleController {
    ArticleModel articleModel = new ArticleModel();
    public void insertArticlesCollection(Article article){
        articleModel.insert(article);
    }
}
