package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BlogServiceImplTest {
    Blog blog;

    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private BlogService blogRepository;

@Test
    void UserCanCreateBlogCanBeTested() {
    CreateBlogRequest requestForm = new CreateBlogRequest();
    requestForm.setName("GistLover blog");
    requestForm.setContent("");
    requestForm.setDescription("");
    blogService.createUserBlog(requestForm);
    assertEquals(1, blogRepository.count());
}
    @Test
    void userCanAddArticleToBlog(){
     Blog blog = new Blog();
   //  blog.addArticle();

    }

}