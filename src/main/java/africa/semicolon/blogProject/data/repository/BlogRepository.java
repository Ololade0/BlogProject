package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.Article;
import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    Blog findBlogByName(String name);
    Blog findBlogById(String id);







}
