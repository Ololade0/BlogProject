package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.Blog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BlogRepositoryTest {
    @Autowired
    private BlogRepository blogRepository;

    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
    }

    @Test
    void testThatBlogCanBeFindByName(){
        Blog blog = new Blog();
        blog.setName("Ololade");
        blogRepository.save(blog);
        assertNotNull(blogRepository.findBlogByName("Ololade"));
        assertEquals("Ololade", blogRepository.findBlogByName("Ololade").getName());


    }
}