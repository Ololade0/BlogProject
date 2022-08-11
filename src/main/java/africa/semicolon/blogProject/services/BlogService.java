package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;
import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;

public interface BlogService {
    CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest);

    long size();

    Article addArticle(AddArticleRequest articleRequest);

    void deleteAll();

    Article viewArticle(ViewArticleRequest viewArticleRequest);


    void deleteArticle(DeleteRequest deleteRequest);


    Blog addComment(AddCommentRequest addCommentRequest);
}
