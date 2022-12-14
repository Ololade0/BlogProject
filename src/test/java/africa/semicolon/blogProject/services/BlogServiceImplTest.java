package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.data.repository.ArticleRepository;
import africa.semicolon.blogProject.data.repository.CommentRepository;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.exceptions.BlogExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentServices commentServices;
    @Autowired
    private ArticleServices articleServices;

    @AfterEach
    void setUp() {


        blogService.deleteAll();
        userService.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    void createBlog_RepositorySizeIncreaseTest() {
        RegisterUserRequest registerUserform = new RegisterUserRequest();
        registerUserform.setUserName("Ololade");
        registerUserform.setPassword("1234");
        registerUserform.setEmail("lolade@gmail.com");
        userService.registerUser(registerUserform);

        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail("lolade@gmail.com");
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
    }

    @Test
    void duplicateBlogNameThrowsExceptionTest() {
        RegisterUserRequest registerUserform = new RegisterUserRequest();
        registerUserform.setUserName("Ololade");
        registerUserform.setPassword("1234");
        registerUserform.setEmail("lolade@gmail.com");
        userService.registerUser(registerUserform);

        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail("lolade@gmail.com");
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
        assertThrows(BlogExistsException.class, () -> blogService.createBlog(createBlogRequest));
        assertEquals(1, blogService.size());

    }

    @Test
    void createBlog_UserCanAddArticleToBlog() {
        //create user
        var request = new RegisterUserRequest();
        request.setUserName("ololade");
        request.setEmail("asake");
        request.setPassword("asaks");
        userService.registerUser(request);

        User user = userService.getUserByEmail("asake");

        assertNull(user.getBlog());

        //add blog to user
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail(user.getEmail());
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
        user = userService.getUserByEmail("asake");
        assertNotNull(user.getBlog());

        // add article to blog
        AddArticleRequest articleRequest = new AddArticleRequest();
        articleRequest.setUserId(user.getId());
        articleRequest.setTitle("GistLoverBlog");
        articleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        blogService.addArticle(articleRequest);

        user = userService.getUserByEmail("asake");

        assertEquals(1, user.getBlog().getArticles().size());
        assertEquals("GistLoverBlog", userService.getUserByEmail("asake").getBlog().getArticles().get(0).getTitle());

    }

    @Test
    void createBlog_UserCanAddArticle_UserCanViewOneArticle() {
        //create user
        var request = new RegisterUserRequest();
        request.setUserName("ololade");
        request.setEmail("asake");
        request.setPassword("asaks");
        userService.registerUser(request);
        User user = userService.getUserByEmail("asake");
        assertNull(user.getBlog());

        //add blog to user
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail(user.getEmail());
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
        user = userService.getUserByEmail("asake");
        assertNotNull(user.getBlog());

        // add article to blog
        AddArticleRequest articleRequest = new AddArticleRequest();
        articleRequest.setUserId(user.getId());
        articleRequest.setTitle("Fear");
        articleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        blogService.addArticle(articleRequest);
        user = userService.getUserByEmail("asake");

        ViewArticleRequest viewArticleRequest = new ViewArticleRequest();
        viewArticleRequest.setUserEmail(user.getEmail());
        viewArticleRequest.setTitle("Gistlover");
        viewArticleRequest.setUserId("1234");

        blogService.viewArticle(viewArticleRequest);
        assertEquals(1, user.getBlog().getArticles().size());
        assertEquals("Fear", userService.getUserByEmail("asake").getBlog().getArticles().get(0).getTitle());

    }

    @Test
    void createBlog_UserCanAddArticle_UserCanViewAllArticle() {
        //create user
        var request = new RegisterUserRequest();
        request.setUserName("ololade");
        request.setEmail("asake");
        request.setPassword("asaks");
        userService.registerUser(request);

        User user = userService.getUserByEmail("asake");

        assertNull(user.getBlog());

        //add blog to user
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail(user.getEmail());
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
        user = userService.getUserByEmail("asake");
        assertNotNull(user.getBlog());

        // add article to blog
        AddArticleRequest articleRequest = new AddArticleRequest();
        articleRequest.setUserId(user.getId());
        articleRequest.setTitle("Fear");
        articleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        blogService.addArticle(articleRequest);
        user = userService.getUserByEmail("asake");


        ViewAllArticleRequest viewAllArticleRequest = new ViewAllArticleRequest();
        viewAllArticleRequest.setUserId("1234");
        viewAllArticleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        viewAllArticleRequest.setTitle("Fear");
        blogService.viewAllArticles(viewAllArticleRequest);


        assertEquals(1, user.getBlog().getArticles().size());
        assertEquals("Fear", userService.getUserByEmail("asake").getBlog().getArticles().get(0).getTitle());

    }

    @Test
    void createBlog_UserCanDeleteArticle() {
        //create user
        var request = new RegisterUserRequest();
        request.setUserName("ololade");
        request.setEmail("asake");
        request.setPassword("asaks");
        userService.registerUser(request);
        User user = userService.getUserByEmail("asake");

        assertNull(user.getBlog());

        //add blog to user
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail(user.getEmail());
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());

        AddArticleRequest articleRequest = new AddArticleRequest();
        articleRequest.setUserId(user.getId());
        articleRequest.setTitle("GistLoverBlog");
        articleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        blogService.addArticle(articleRequest);
        user = userService.getUserByEmail("asake");


        DeleteRequest deleteRequest = new DeleteRequest();
        var article = blogService.getArticleByTitle("GistLoverBlog");

        deleteRequest.setId(user.getBlog().getId());
        deleteRequest.setArticleId(article.getId());

        blogService.deleteArticle(deleteRequest);

        assertEquals(0, blogService.getALlArticles().size());



    }

    @Test
    void createBlog_UserCanAddComment() {
        var request = new RegisterUserRequest();
        request.setUserName("ololade");
        request.setEmail("asake");
        request.setPassword("asaks");
        userService.registerUser(request);

        User user = userService.getUserByEmail("asake");

        assertNull(user.getBlog());

        //add blog to user
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        createBlogRequest.setName("Ololade");
        createBlogRequest.setUserEmail(user.getEmail());
        blogService.createBlog(createBlogRequest);
        assertEquals(1, blogService.size());
        //  assertNotNull(user.getBlog());

        // add article to blog
        AddArticleRequest articleRequest = new AddArticleRequest();
        articleRequest.setUserId(user.getId());
        articleRequest.setTitle("GistLoverBlog");
        articleRequest.setBody("The fear of gistlover, is the fear of wisdom");
        blogService.addArticle(articleRequest);


        AddCommentRequest addCommentRequest = new AddCommentRequest();
        addCommentRequest.setBlogName("Oba");
        addCommentRequest.setBody("I love your blog");
        addCommentRequest.setTitle("Gistlover");
        var comment = commentServices.addComment(addCommentRequest);
        assertEquals("I love your blog", comment.getBody());
        assertEquals(1, commentRepository.count());

    }


}


