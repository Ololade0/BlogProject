package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;

import africa.semicolon.blogProject.dtos.requests.AddArticleRequest;

import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;
import africa.semicolon.blogProject.dtos.requests.ViewArticleRequest;

public interface ArticleServices {
    Article saveArticle(AddArticleRequest articleRequest);

    Article viewArticle(ViewArticleRequest viewArticleRequest);


    void deleteArticle(Article article);


    Article saveComment(AddCommentRequest addCommentRequest);


}