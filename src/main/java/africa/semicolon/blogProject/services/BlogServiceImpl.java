package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;
import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.data.model.User;

import africa.semicolon.blogProject.data.repository.ArticleRepository;
import africa.semicolon.blogProject.data.repository.BlogRepository;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;

import africa.semicolon.blogProject.exceptions.BlogExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleServices articleServices;
    @Autowired
    private ArticleRepository articleRepository;
;

    @Override
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
        isExist(createBlogRequest.getName());
        User user = userService.getUserByEmail(createBlogRequest.getUserEmail());
        Blog blog = new Blog();
        blog.setName(createBlogRequest.getName());
        var blog_ = blogRepository.save(blog);
        user.setBlog(blog_);
        userService.reSave(user);
        CreateBlogResponse createBlogResponse = new CreateBlogResponse();
        createBlogResponse.setMessage("Blog successfully created");
        return createBlogResponse;

    }

    private void isExist(String name) {
        Blog savedBlog = blogRepository.findBlogByName(name);
        if (savedBlog != null) throw new BlogExistsException(name + "Already exist");
    }

    @Override
    public long size() {
        return blogRepository.count();
    }

    @Override
    public Article  addArticle(AddArticleRequest articleRequest) {
        User user = userService.getUserById(articleRequest.getUserId());
        Blog blog = user.getBlog();
        var savedArticle = articleServices.saveArticle(articleRequest);
        blog.getArticles().add(savedArticle);
        blogRepository.save(blog);
        userService.reSave(user);
        return savedArticle;
    }



    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }

    @Override
    public Article viewArticle(ViewArticleRequest viewArticleRequest) {
        User user = userService.getUserByEmail(viewArticleRequest.getUserEmail());
        Blog blog = user.getBlog();
        return articleServices.viewArticle(viewArticleRequest);

    }

    @Override
    public void deleteArticle(DeleteRequest deleteRequest) {
        Blog blog = blogRepository.findBlogByName(deleteRequest.getName());
        List<Article> articles =  blog.getArticles();
        Article article = new Article();
        for (int i = 0; i < articles.size() ; i++) {
            if(articles.get(i).getTitle().equalsIgnoreCase(deleteRequest.getTitle()));
            article = articles.get(i);
            articles.remove(i);
        }
        blog.setArticles(articles);
        articleServices.deleteArticle(article);
        blogRepository.save(blog);

    }

    @Override
    public Blog addComment(AddCommentRequest addCommentRequest) {
      Blog blog =  blogRepository.findBlogByName(addCommentRequest.getBlogName());
        Article article = articleServices.saveComment(addCommentRequest);
        blog.getArticles().add(article);
         return blogRepository.save(blog);

    }
    }




