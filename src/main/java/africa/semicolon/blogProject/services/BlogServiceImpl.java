package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Article;
import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.data.repository.ArticleRepository;
import africa.semicolon.blogProject.data.repository.BlogRepository;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.dtos.responses.AddArticleResponse;
import africa.semicolon.blogProject.dtos.responses.AddCommentResponse;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;
import africa.semicolon.blogProject.exceptions.BlogExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public AddArticleResponse addArticle(AddArticleRequest articleRequest) {
        User user = userService.getUserById(articleRequest.getUserId());
        Blog blog = user.getBlog();
        var savedArticle = articleServices.saveArticle(articleRequest);
        blog.getArticles().add(savedArticle);
        blogRepository.save(blog);
        userService.reSave(user);

        AddArticleResponse addArticleResponse = new AddArticleResponse();
        addArticleResponse.setMessage("Article successfully added");
        return addArticleResponse;
    }


    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }

    @Override
    public Optional<Article> viewArticle(ViewArticleRequest viewArticleRequest) {
        return articleRepository.findById(viewArticleRequest.getUserId());

    }


    @Override
    public AddCommentResponse addComment(AddCommentRequest addCommentRequest) {
        Article article = articleServices.saveComment(addCommentRequest);
        Blog blog = blogRepository.findBlogByName(addCommentRequest.getBlogName());

        blog.getArticles().add(article);
        blogRepository.save(blog);
        AddCommentResponse addCommentResponse = new AddCommentResponse();
        addCommentResponse.setMessage("Blog successfully created");
        return addCommentResponse;


    }

    @Override
    public List<Article> viewAllArticles(ViewAllArticleRequest viewAllArticleRequest) {
        return articleRepository.findAll();

    }

    @Override
    public String deleteArticle(DeleteRequest deleteRequest) {
        var foundBlog = blogRepository.findBlogById(deleteRequest.getId());
        if (foundBlog != null) {
            for (var articleToDel : foundBlog.getArticles()) {
                if (Objects.equals(articleToDel.getId(), deleteRequest.getArticleId())){
                    articleServices.deleteArticle(articleToDel);
                    foundBlog.getArticles().remove(articleToDel);
                    blogRepository.save(foundBlog);
                    return "deleted successfully";
                }

            }
        }
        return "error";
    }

    @Override
    public Article getArticleByTitle(String title) {
        return articleRepository.findArticleByTitle(title);
    }

    @Override
    public List<Article> getALlArticles() {
        return articleServices.findAll();
    }

}