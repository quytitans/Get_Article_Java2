package VNexpressArticles.model;

import VNexpressArticles.unity.Article;
import VNexpressArticles.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleModel {
//viet ham lay toan bo thong tin article ve

    public List getAll(){
        List<Article> articles = new ArrayList<>();
        try {
            Article article1 = new Article();
            Connection cnn = ConnectionHelper.getConnection();
            Statement stt = cnn.createStatement();
            String sql = "sleclect * from article001";
            ResultSet resultSet = stt.executeQuery(sql);
            while (resultSet.next()){
                article1.setUrl(resultSet.getString("url"));
                article1.setContent(resultSet.getString("content"));
                article1.setCreatedAt(resultSet.getDate("createdAt"));
                article1.setUpdatedAt(resultSet.getDate("updateAt"));
                article1.setStatus(resultSet.getInt("status"));
                article1.setTitle(resultSet.getString("title"));
                article1.setDescription(resultSet.getString("description"));
                article1.setThumbnail(resultSet.getString("thumbnail"));
                articles.add(article1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articles;
    }

    public boolean insert(Article article) {
        try {
            // Login và kết nối -> đối tượng của lớp Connection.
            Connection cnn = ConnectionHelper.getConnection();

            PreparedStatement preparedStatement = cnn.prepareStatement(
                    "insert into article001 (url, title, description, thumbnail, content, createdAt, updateAt, status) " +
                            "values (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, article.getUrl());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getDescription());
            preparedStatement.setString(4, article.getThumbnail());
            preparedStatement.setString(5, article.getContent());
            preparedStatement.setString(6, article.getCreatedAtStr());
            preparedStatement.setString(7, article.getUpdatedAtStr());
            preparedStatement.setInt(8, article.getStatus()); //0. chua hoan thanh 1.da hoan thanh -1.da xoa
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
