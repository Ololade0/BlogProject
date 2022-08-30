package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.dtos.responses.AddArticleResponse;
import africa.semicolon.blogProject.dtos.responses.AddCommentResponse;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;
import com.mongodb.client.model.Aggregates;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest);

    long size();

    AddArticleResponse addArticle(AddArticleRequest articleRequest);

    void deleteAll();

    Optional<Article> viewArticle(ViewArticleRequest viewArticleRequest);


    AddCommentResponse addComment(AddCommentRequest addCommentRequest);


    List<Article> viewAllArticles(ViewAllArticleRequest viewAllArticleRequest);


    String deleteArticle(DeleteRequest deleteRequest);

    Article getArticleByTitle(String gistLoverBlog);

    List<Article> getALlArticles();
}
