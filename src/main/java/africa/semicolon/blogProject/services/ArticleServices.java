package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;

import africa.semicolon.blogProject.dtos.requests.AddArticleRequest;

import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;
import africa.semicolon.blogProject.dtos.requests.ViewArticleRequest;

import java.util.List;
import java.util.Optional;

public interface ArticleServices {
    Article saveArticle(AddArticleRequest articleRequest);

    Optional<Article> viewArticle(ViewArticleRequest viewArticleRequest);


    void deleteArticle(Article article);


    Article saveComment(AddCommentRequest addCommentRequest);


    List<Article> getAllArticles();

    long size();

    List<Article> findAll();
}