package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.data.repository.ArticleRepository;

import africa.semicolon.blogProject.dtos.requests.AddArticleRequest;

import africa.semicolon.blogProject.dtos.requests.AddCommentRequest;
import africa.semicolon.blogProject.dtos.requests.ViewArticleRequest;

import africa.semicolon.blogProject.exceptions.ArticleDoesNotExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ArticleServiceImpl implements ArticleServices {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentServices commentService;

    @Override
    public Article saveArticle(AddArticleRequest articleRequest) {
        Article article = new Article();
        article.setBody(articleRequest.getBody());
        article.setTitle(articleRequest.getTitle());
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> viewArticle(ViewArticleRequest viewArticleRequest) {
        Optional<Article> article = articleRepository.findById(viewArticleRequest.getUserId());
        if (article.isPresent()) {
            return article;

        }

        throw new ArticleDoesNotExistsException(article + "does not exist");

    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }




    @Override
    public Article saveComment(AddCommentRequest addCommentRequest) {

        Comment comment = commentService.addComment(addCommentRequest);
        Article article = articleRepository.findArticleByTitle(addCommentRequest.getTitle());
        if (Objects.equals(article.getBlogName(), addCommentRequest.getBlogName())){
            article.getComments().add(comment);

        }
        return articleRepository.save(article);

    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public long size() {
        return articleRepository.count();
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


}

















