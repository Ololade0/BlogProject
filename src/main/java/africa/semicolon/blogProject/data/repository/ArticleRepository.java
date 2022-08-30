package africa.semicolon.blogProject.data.repository;

import africa.semicolon.blogProject.data.model.Article;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {
    Article findArticleByTitle(String title);


}
